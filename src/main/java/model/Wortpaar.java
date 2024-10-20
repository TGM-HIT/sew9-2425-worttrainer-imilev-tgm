package model;

import java.net.URI;
import java.net.URISyntaxException;

public class Wortpaar {
    private String url;
    private String wort;

    // Standard-Konstruktor für Jackson
    public Wortpaar() {
        // Standard-Konstruktor, der benötigt wird für JSON Converter
    }

    public Wortpaar(String url, String wort) throws URISyntaxException {
        setUrl(url);
        setWort(wort);
    }

    private void setWort(String wort) {
        if (wort == null || wort.isEmpty()) {
            throw new IllegalArgumentException("Wort darf nicht leer sein");
        }
        this.wort = wort;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) throws URISyntaxException {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("URL darf nicht leer oder null sein");
        }

        try {
            URI uri = new URI(url);
            this.url = url;
        } catch (URISyntaxException e) {
            throw new URISyntaxException("Ungültige URL: " + url, e.toString());
        }
    }

    public String getWort() {
        return wort;
    }
}
