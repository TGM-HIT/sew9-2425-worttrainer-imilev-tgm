package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

/**
 * @author Ivan Milev
 * @version 2024-10-20
 * Der Worttrainer, der sich um die Wortpaare kümmert.
 */
public class WortTrainer {

    private int richtig;
    private int insgesamt;
    private int aktuellesWortpaarIndex; // Der Index des aktuellen Wortpaars
    private ArrayList<Wortpaar> wortpaare;

    public WortTrainer() {
        this.wortpaare = new ArrayList<>();
        this.aktuellesWortpaarIndex = -1; // -1 bedeutet, dass kein Wortpaar gewählt wurde
    }

    public void waehleWortpaar(int index) {
        if (index < 0 || index >= wortpaare.size()) {
            throw new IndexOutOfBoundsException("Ungültiger Index: " + index);
        }
        this.aktuellesWortpaarIndex = index;
    }

    public void waehleRandomWortpaar() {
        if (!wortpaare.isEmpty()) {
            waehleWortpaar((int) (Math.random() * this.wortpaare.size()));
        }
    }

    public int getRichtig() {
        return richtig;
    }

    public int getInsgesamt() {
        return insgesamt;
    }

    public void setRichtig(int richtig) {
        if (richtig < 0) {
            throw new IllegalArgumentException("Zahl darf nicht negativ sein");
        }
        this.richtig = richtig;
    }

    public void setInsgesamt(int insgesamt) {
        if (insgesamt < 0) {
            throw new IllegalArgumentException("Score darf nicht negativ sein");
        }
        this.insgesamt = insgesamt;
    }

    public int getAktuellesWortpaarIndex() {
        return aktuellesWortpaarIndex;
    }

    public void setAktuellesWortpaarIndex(int index) {
        this.aktuellesWortpaarIndex = index;
    }

    public void addWortpaar(Wortpaar wortpaar) {
        this.wortpaare.add(wortpaar);
    }
    @JsonIgnore     // Damit die Getter für die Serialization beim JSON ignoriert werden fügen wir diese Notation hinzu
    public String getBildURL() {
        if (aktuellesWortpaarIndex >= 0 && aktuellesWortpaarIndex < wortpaare.size()) {
            return wortpaare.get(aktuellesWortpaarIndex).getUrl();
        }
        return null; // Rückgabe null, wenn kein aktuelles Wortpaar vorhanden ist
    }

    @JsonIgnore // Damit die Getter für die Serialization beim JSON ignoriert werden fügen wir diese Notation hinzu
    public String getWort() {
        if (aktuellesWortpaarIndex >= 0 && aktuellesWortpaarIndex < wortpaare.size()) {
            return wortpaare.get(aktuellesWortpaarIndex).getWort();
        }
        return null; // Rückgabe null, wenn kein aktuelles Wortpaar vorhanden ist
    }

    public ArrayList<Wortpaar> getWortpaare() {
        return wortpaare;
    }
}
