package main;

import model.WortTrainer;
import model.Wortpaar;
import saveload.SaveLoad;
import saveload.SaveLoadJson;
import view.View;

import java.io.IOException;

/**
 * Die Hauptklasse, die den Worttrainer startet.
 */
public class Main {
    public static void main(String[] args) {
        // Erstelle ein SaveLoad-Objekt
        SaveLoad saveLoad = new SaveLoadJson();

        // Versuche, den WortTrainer zu laden. Falls nicht vorhanden, erstelle einen neuen.
        WortTrainer wortTrainer = loadWortTrainer(saveLoad);

        // Erstelle eine Instanz der View-Klasse
        View wortTrainerView = new View(wortTrainer, saveLoad);

        // Starte den Worttrainer
        wortTrainerView.start();
    }

    /**
     * Lädt den WortTrainer aus persistierten Daten oder erstellt einen neuen.
     */
    private static WortTrainer loadWortTrainer(SaveLoad saveLoad) {
        try {
            WortTrainer wortTrainer = saveLoad.load();
            if (wortTrainer == null) {
                return createNewWortTrainer();
            }
            return wortTrainer;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Keine Daten gefunden, neuer Worttrainer wird erstellt.");
            return createNewWortTrainer();
        }
    }

    /**
     * Erstellt einen neuen WortTrainer mit fix einprogrammierten Wortpaaren.
     */
    private static WortTrainer createNewWortTrainer() {
        WortTrainer wt = new WortTrainer();
        try {
            wt.addWortpaar(new Wortpaar("https://e6ki4fbm3oz.exactdn.com/wp-content/uploads/2016/06/Zunge-Giraffe.jpg?strip=all&lossy=1&ssl=1", "Giraffe"));
            wt.addWortpaar(new Wortpaar("https://i.pinimg.com/550x/cb/08/80/cb0880dbcb4b62c1a9aec4aca3ad94df.jpg", "Papagei"));
            wt.addWortpaar(new Wortpaar("https://i.schicksal.com/var/jupiter/storage/images/astrologie/chinesisches-horoskop/affe/1214199-92-ger-DE/Affe_16by9-fullhd.jpg", "Affe"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wt;
    }
}
