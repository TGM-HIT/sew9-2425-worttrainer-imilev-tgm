package model;


import java.net.URI;
import java.net.URISyntaxException;

public class Wortpaar {
    private String url;
    private String wort;

    public Wortpaar(String url, String wort) throws URISyntaxException {
        setUrl(url);
        setWort(wort);


    }

    private void setWort(String wort) {
        if(wort != null && !wort.isEmpty()) {
            this.wort = wort;
        }
        else {
            throw new IllegalArgumentException("Wort darf nicht null sein");
        }

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) throws URISyntaxException {
        if (url != null && !url.isEmpty()) {
            try {
                URI uri = new URI(url);
                this.url = url;
            } catch (URISyntaxException e) {
                throw new URISyntaxException("Invalid URL: " + url, e.toString());
            }
        } else {
            throw new IllegalArgumentException("URL darf nicht leer oder null sein");
        }
    }

    public String getWort() {
        return wort;
    }
}
