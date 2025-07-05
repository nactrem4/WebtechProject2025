package com.example.demo.web.api;

public class TastaturCreateRequest {
    private String tastaturName;
    private String modell;
    private String switches;
    private String keycaps;
    private String beschreibung;
    private String bild; // Base64-String statt byte[]

    public TastaturCreateRequest() {}

    public TastaturCreateRequest(String tastaturName, String modell, String switches, String keycaps, String beschreibung, String bild) {
        this.tastaturName = tastaturName;
        this.modell = modell;
        this.switches = switches;
        this.keycaps = keycaps;
        this.beschreibung = beschreibung;
        this.bild = bild;
    }

    // Getter und Setter
    public String getTastaturName() { return tastaturName; }
    public void setTastaturName(String tastaturName) { this.tastaturName = tastaturName; }

    public String getModell() { return modell; }
    public void setModell(String modell) { this.modell = modell; }

    public String getSwitches() { return switches; }
    public void setSwitches(String switches) { this.switches = switches; }

    public String getKeycaps() { return keycaps; }
    public void setKeycaps(String keycaps) { this.keycaps = keycaps; }

    public String getBeschreibung() { return beschreibung; }
    public void setBeschreibung(String beschreibung) { this.beschreibung = beschreibung; }

    public String getBild() { return bild; }
    public void setBild(String bild) { this.bild = bild; }
}
