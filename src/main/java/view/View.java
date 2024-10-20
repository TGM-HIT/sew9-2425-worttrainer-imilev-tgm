package view;

import model.WortTrainer;
import saveload.SaveLoad;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Ivan Milev
 * @version 2024-10-20
 * Diese Klasse bildet die Darstellung bzw die Benutzeroberfläche für die User da
 */
public class View {

    private WortTrainer wortTrainer;
    private SaveLoad saveLoad;
    private JFrame frame;
    private JPanel panel;
    private JLabel statsLabel;
    private JLabel imageLabel;
    private JTextField inputField;
    private JButton submitButton;
    private JButton stopButton;

    private int i = -1;

    public View(WortTrainer wortTrainer, SaveLoad saveLoad) {
        this.wortTrainer = wortTrainer;
        this.saveLoad = saveLoad;

        // Initialisierung des JFrame
        frame = new JFrame("Worttrainer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 400));

        // Initialisierung des Panels
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Statistik-Label
        statsLabel = new JLabel("", SwingConstants.CENTER);
        panel.add(statsLabel, BorderLayout.NORTH);

        // Bild-Label
        imageLabel = new JLabel();
        panel.add(imageLabel, BorderLayout.CENTER);

        // Eingabefeld und Button
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout()); // Vertical layout for the entire panel

        // Input Field
        inputField = new JTextField(20);

        // Buttons
        submitButton = new JButton("Antwort abgeben");
        submitButton.addActionListener(e -> handleAnswer());

        stopButton = new JButton("Programm beenden");
        stopButton.addActionListener(e -> {
            showStatistics();
            saveWortTrainer();
            System.exit(0);
        });

        // Create a horizontal panel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(submitButton);
        buttonPanel.add(stopButton);

        // Add the horizontal button panel to the bottom of the input panel
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(buttonPanel, BorderLayout.PAGE_END);

        // Add the input panel to the main panel
        panel.add(inputPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Startet die Hauptschleife des Worttrainers, in der der Benutzer Wörter raten kann.
     */
    public void start() {
        showNextWord();
    }

    /**
     * Startet die nächste Runde des Worttrainers.
     */
    private void showNextWord() {

        // Dient damit das erste Wortpaar aus dem Index gelesen wird und nicht random

        if (i > 0) {
            wortTrainer.waehleRandomWortpaar();
        }
        i = 1;


        // Zeige das Bild und die bisherige Statistik an
        showImageAndStats();

        // Setze das Eingabefeld zurück
        inputField.setText("");
    }

    /**
     * Handhabt die Eingabe des Benutzers und überprüft die Antwort.
     */
    private void handleAnswer() {
        String userAnswer = inputField.getText();
        if (!userAnswer.isEmpty()) {
            checkAnswer(userAnswer);
            inputField.setText("");
            showNextWord(); // Zeige das nächste Wort
        }
    }

    /**
     * Überprüft die Eingabe des Benutzers und aktualisiert die Statistik.
     */
    private void checkAnswer(String benutzerEingabe) {
        String richtigesWort = wortTrainer.getWort();
        boolean isCorrect = benutzerEingabe.equalsIgnoreCase(richtigesWort);
        String message = isCorrect ? "Richtig!" : "Falsch! Das richtige Wort war: " + richtigesWort;
        JOptionPane.showMessageDialog(frame, message, "Ergebnis", JOptionPane.INFORMATION_MESSAGE);
        if (isCorrect) {
            wortTrainer.setRichtig(wortTrainer.getRichtig() + 1);
        }
        wortTrainer.setInsgesamt(wortTrainer.getInsgesamt() + 1);
    }

    /**
     * Zeigt das Bild und die Statistik an.
     */
    private void showImageAndStats() {
        // Bereite die Statistik vor
        String stats = "Insgesamt: " + wortTrainer.getInsgesamt() + ", Richtig: " + wortTrainer.getRichtig();
        statsLabel.setText(stats);

        // Versuche, das Bild aus der URL des aktuellen Wortpaars anzuzeigen
        String imageUrl = wortTrainer.getBildURL();
        try {
            URL url = new URL(imageUrl);
            ImageIcon icon = new ImageIcon(url);
            Image scaledImage = icon.getImage().getScaledInstance(500, 400, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(frame, stats + "\nFehler beim Laden des Bildes", "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Zeigt die Statistiken an, bevor das Programm beendet wird.
     */
    private void showStatistics() {
        String stats = "Insgesamt: " + wortTrainer.getInsgesamt() + ", Richtig: " + wortTrainer.getRichtig();
        JOptionPane.showMessageDialog(frame, stats, "Deine Statistik", JOptionPane.INFORMATION_MESSAGE);
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
