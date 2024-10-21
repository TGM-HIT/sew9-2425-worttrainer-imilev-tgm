import model.WortTrainer;
import model.Wortpaar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WortTrainerTest {

    private WortTrainer wortTrainer;

    @BeforeEach
    void setUp() {
        wortTrainer = new WortTrainer();
    }

    @Test
    void testAnfangsZustand() {
        // Überprüfe den Anfangszustand des WortTrainers
        assertEquals(0, wortTrainer.getRichtig());
        assertEquals(0, wortTrainer.getInsgesamt());
        assertEquals(-1, wortTrainer.getAktuellesWortpaarIndex());
        assertTrue(wortTrainer.getWortpaare().isEmpty()); // Die Liste sollte leer sein
    }

    @Test
    void wortpaareHinzufuegen() throws URISyntaxException {
        Wortpaar wortpaar1 = new Wortpaar("https://example.com/image1.jpg", "Beispiel1");
        Wortpaar wortpaar2 = new Wortpaar("https://example.com/image2.jpg", "Beispiel2");
        wortTrainer.addWortpaar(wortpaar1);
        wortTrainer.addWortpaar(wortpaar2);

        assertEquals(2, wortTrainer.getWortpaare().size());
    }

    @Test
    void ausLeererListeWaehlen() {
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            wortTrainer.waehleWortpaar(0); // Versuch, aus einer leeren Liste auszuwählen
        });

        assertEquals("Ungültiger Index: 0", exception.getMessage());
    }

    @Test
    void ausListeAuswaehlen() throws URISyntaxException {
        Wortpaar wortpaar = new Wortpaar("https://example.com/image1.jpg", "Beispiel1");
        wortTrainer.addWortpaar(wortpaar);
        wortTrainer.waehleWortpaar(0); // Wählen des ersten Wortpaares

        assertEquals(0, wortTrainer.getAktuellesWortpaarIndex());
        assertEquals("Beispiel1", wortTrainer.getWort()); // Überprüfe das Wort
    }

    @Test
    void testSetRichtigAndInsgesamt() {
        wortTrainer.setRichtig(3);
        wortTrainer.setInsgesamt(5);

        assertEquals(3, wortTrainer.getRichtig());
        assertEquals(5, wortTrainer.getInsgesamt());
    }

    @Test
    void testSetAktuellesWortpaarIndex() throws URISyntaxException {
        Wortpaar wortpaar = new Wortpaar("https://example.com/image1.jpg", "Beispiel1");
        wortTrainer.addWortpaar(wortpaar);
        wortTrainer.setAktuellesWortpaarIndex(0); // Setze auf gültigen Index

        assertEquals(0, wortTrainer.getAktuellesWortpaarIndex());
    }


    @Test
    void testLeereListeReturnen() {
        assertNull(wortTrainer.getWort()); // Sollte null zurückgeben, da die Liste leer ist
    }

    @Test
    void testLeeresBildReturnen() {
        assertNull(wortTrainer.getBildURL()); // Sollte null zurückgeben, da die Liste leer ist
    }

    @Test
    void wortPaarAussuchen() throws URISyntaxException {
        Wortpaar wortpaar1 = new Wortpaar("https://example.com/image1.jpg", "Beispiel1");
        Wortpaar wortpaar2 = new Wortpaar("https://example.com/image2.jpg", "Beispiel2");
        wortTrainer.addWortpaar(wortpaar1);
        wortTrainer.addWortpaar(wortpaar2);

        wortTrainer.waehleWortpaar(1); // Wählen des zweiten Wortpaares

        assertEquals(1, wortTrainer.getAktuellesWortpaarIndex());
        assertEquals("Beispiel2", wortTrainer.getWort()); // Überprüfe das Wort des zweiten Wortpaares
        assertEquals("https://example.com/image2.jpg", wortTrainer.getBildURL()); // Überprüfe die Bild-URL
    }
}
