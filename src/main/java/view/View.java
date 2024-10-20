package view;

import model.Wortpaar;
import model.WortTrainer;
import saveload.SaveLoad;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Die View-Klasse, die für die Benutzerinteraktion verantwortlich ist.
 * Sie verwendet JOptionPane, um dem Benutzer Eingaben zu ermöglichen und Informationen anzuzeigen.
 */
public class View {

    private WortTrainer wortTrainer;
    private SaveLoad saveLoad;
    private JFrame frame; // JFrame für die Benutzeroberfläche
    private JPanel panel; // JPanel für die Anzeige von Bild und Statistik
    private JLabel statsLabel; // Label für Statistiken
    private JLabel imageLabel; // Label für das Bild
    private JButton stopButton; // Button zum Beenden des Programms

    public View(WortTrainer wortTrainer, SaveLoad saveLoad) {
        this.wortTrainer = wortTrainer;
        this.saveLoad = saveLoad;

        // JFrame initialisieren
        frame = new JFrame("Worttrainer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(600, 400)); // Größe des Fensters festlegen

        // JPanel initialisieren
        panel = new JPanel();
        panel.setLayout(new BorderLayout()); // Layout für das Panel festlegen

        // Statistik-Label
        statsLabel = new JLabel("", SwingConstants.CENTER);
        panel.add(statsLabel, BorderLayout.NORTH);

        // Bild-Label
        imageLabel = new JLabel();
        panel.add(imageLabel, BorderLayout.CENTER);

        // Button zum Beenden des Programms
        stopButton = new JButton("Aufhören");
        stopButton.addActionListener(e -> {
            saveWortTrainer(); // Zustand speichern
            System.exit(0); // Programm beenden
        });
        panel.add(stopButton, BorderLayout.SOUTH);

        frame.add(panel);
        frame.pack(); // JFrame anpassen
        frame.setVisible(true); // JFrame sichtbar machen
    }

    /**
     * Startet die Hauptschleife des Worttrainers, in der der Benutzer Wörter raten kann.
     */
    public void start() {
        boolean ersterVersuch = true;
        String letzteAntwort = null;

        while (true) {
            // Wähle zufällig ein Wortpaar aus
            wortTrainer.waehleRandomWortpaar();

            // Zeige das Bild und die bisherige Statistik an
            showImageAndStats(ersterVersuch, letzteAntwort);

            // Frage das Wort vom Benutzer ab
            String benutzerEingabe = JOptionPane.showInputDialog(frame, "Was siehst du auf dem Bild?", "Rate das Wort", JOptionPane.QUESTION_MESSAGE);

            // Wenn der Benutzer die Eingabe abbricht oder nichts eingibt, breche die Schleife ab
            if (benutzerEingabe == null || benutzerEingabe.isEmpty()) {
                break;
            }

            // Überprüfe die Eingabe und zeige das Ergebnis an
            if (checkAnswer(benutzerEingabe)) {
                letzteAntwort = null;  // Reset
            } else {
                letzteAntwort = benutzerEingabe;
            }

            // Inkrementiere den Zähler für die Gesamtversuche
            wortTrainer.setInsgesamt(wortTrainer.getInsgesamt() + 1);
        }

        // Zeige die Endstatistik an und das Stop-Button
        showEndScreen();
    }

    /**
     * Überprüft die Eingabe des Benutzers und aktualisiert die Statistik.
     * Gibt true zurück, wenn die Antwort richtig war.
     */
    private boolean checkAnswer(String benutzerEingabe) {
        if (benutzerEingabe.equalsIgnoreCase(wortTrainer.getWort())) {
            wortTrainer.setRichtig(wortTrainer.getRichtig() + 1);
            JOptionPane.showMessageDialog(frame, "Richtig!", "Ergebnis", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(frame, "Falsch! Das richtige Wort war: " + wortTrainer.getWort(), "Ergebnis", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Zeigt das Bild und die Statistik an.
     */
    private void showImageAndStats(boolean ersterVersuch, String letzteAntwort) {
        // Bereite die Statistik vor
        String stats = "Insgesamt: " + wortTrainer.getInsgesamt() + ", Richtig: " + wortTrainer.getRichtig();
        if (!ersterVersuch && letzteAntwort != null) {
            stats += "\nLetzte Eingabe war falsch: " + letzteAntwort;
        }

        // Update the statistics label
        statsLabel.setText(stats);

        // Versuche, das Bild aus der URL anzuzeigen
        String imageUrl = wortTrainer.getBildURL();
        try {
            URL url = new URL(imageUrl);
            ImageIcon icon = new ImageIcon(url);
            Image scaledImage = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH); // Bild skalieren
            imageLabel.setIcon(new ImageIcon(scaledImage)); // Skaliertes Bild setzen
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(frame, stats + "\nFehler beim Laden des Bildes", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Zeigt die Endstatistik an und ermöglicht es dem Benutzer, das Programm zu beenden.
     */
    private void showEndScreen() {
        statsLabel.setText("Vielen Dank fürs Spielen! Hier sind deine Statistiken: " +
                "\nInsgesamt: " + wortTrainer.getInsgesamt() + ", Richtig: " + wortTrainer.getRichtig());
        imageLabel.setIcon(null); // Bild entfernen
    }

    /**
     * Speichert den aktuellen Zustand des Worttrainers.
     */
    private void saveWortTrainer() {
        try {
            saveLoad.save(wortTrainer);
            System.out.println("WortTrainer erfolgreich gespeichert.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fehler beim Speichern des WortTrainers.");
        }
    }
}
