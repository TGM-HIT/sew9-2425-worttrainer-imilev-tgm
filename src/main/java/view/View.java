package view;

import model.Wortpaar;
import model.WortTrainer;
import saveload.SaveLoad;

import javax.swing.*;
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

    public View(WortTrainer wortTrainer, SaveLoad saveLoad) {
        this.wortTrainer = wortTrainer;
        this.saveLoad = saveLoad;
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
            String benutzerEingabe = JOptionPane.showInputDialog(null, "Was siehst du auf dem Bild?", "Rate das Wort", JOptionPane.QUESTION_MESSAGE);

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

            // Frage den Benutzer, ob er weitermachen möchte
            int weiter = JOptionPane.showConfirmDialog(null, "Möchtest du weitermachen?", "Fortfahren", JOptionPane.YES_NO_OPTION);
            if (weiter == JOptionPane.NO_OPTION) {
                break;
            }
            ersterVersuch = false;  // Es war nicht der erste Versuch
        }

        // Speichere den aktuellen Zustand des Worttrainers
        saveWortTrainer();
    }

    /**
     * Überprüft die Eingabe des Benutzers und aktualisiert die Statistik.
     * Gibt true zurück, wenn die Antwort richtig war.
     */
    private boolean checkAnswer(String benutzerEingabe) {
        if (benutzerEingabe.equalsIgnoreCase(wortTrainer.getWort())) {
            wortTrainer.setRichtig(wortTrainer.getRichtig() + 1);
            JOptionPane.showMessageDialog(null, "Richtig!", "Ergebnis", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Falsch! Das richtige Wort war: " + wortTrainer.getWort(), "Ergebnis", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Zeigt das Bild, die Statistik und das Ergebnis des letzten Versuchs an (falls nicht der erste Versuch).
     */
    private void showImageAndStats(boolean ersterVersuch, String letzteAntwort) {
        String stats = "Insgesamt: " + wortTrainer.getInsgesamt() + ", Richtig: " + wortTrainer.getRichtig();
        if (!ersterVersuch && letzteAntwort != null) {
            stats += "\nLetzte Eingabe war falsch: " + letzteAntwort;
        }
        String imageUrl = wortTrainer.getBildURL();

        // Versuche, das Bild aus der URL anzuzeigen
        try {
            URL url = new URL(imageUrl);
            ImageIcon icon = new ImageIcon(url);
            JOptionPane.showMessageDialog(null, stats, "Statistik und Bild", JOptionPane.INFORMATION_MESSAGE, icon);
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, stats + "\nFehler beim Laden des Bildes", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
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
