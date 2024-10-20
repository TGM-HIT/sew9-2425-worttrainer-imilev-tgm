package model;

import java.util.ArrayList;

/**
 * @author Ivan Milev
 * @version 2024-10-20
 * Der Worttrainer der sich um die Wortpaare kümmert.
 */
public class WortTrainer {

    private int richtig;
    private int insgesamt;
    private Wortpaar wortpaar;

    private ArrayList<Wortpaar> wortpaare;

    public WortTrainer() {
        this.wortpaare = new ArrayList<>();
    }

    public void waehleWortpaar(int index) {
        this.wortpaar = wortpaare.get(index);
    }

    public void waehleRandomWortpaar() {
        waehleWortpaar((int) (Math.random() * this.wortpaare.size()));
    }

    public int getRichtig() {
        return richtig;
    }

    public int getInsgesamt() {
        return insgesamt;
    }

    public void setRichtig(int richtig) {
        if(richtig < 0) {
            throw new IllegalArgumentException("Zahl darf nicht negativ sein");
        }
        this.richtig = richtig;
    }

    public void setInsgesamt(int insgesamt) {
        if(insgesamt < 0) {
            throw new IllegalArgumentException("Score darf nicht negative sein");
        }
        this.insgesamt = insgesamt;
    }

    public void addWortpaar(Wortpaar wortpaar) {
        this.wortpaare.add(wortpaar);
    }

    public String getBildURL() {
        return this.wortpaar.getUrl();
    }

    public String getWort() {
        return this.wortpaar.getWort();
    }
}