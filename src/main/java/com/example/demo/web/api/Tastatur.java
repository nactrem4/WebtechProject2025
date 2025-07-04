package com.example.demo.web.api;

public class Tastatur {
    private Long id;
    private String tastaturName;
    private String modell;
    private String switches;
    private String keycaps;
    private String beschreibung;
    private String bildUrl;

    private byte[] bild;

    public Tastatur(Long id, String tastaturName, String modell, String switches, String keycaps, String beschreibung, String bildUrl, byte[] bild) {
        this.id = id;
        this.tastaturName = tastaturName;
        this.modell = modell;
        this.switches = switches;
        this.keycaps = keycaps;
        this.beschreibung = beschreibung;
        this.bildUrl = bildUrl;
        this.bild = bild;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTastaturName() {
        return tastaturName;
    }

    public void setTastaturName(String tastaturName) {
        this.tastaturName = tastaturName;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public String getSwitches() {
        return switches;
    }

    public void setSwitches(String switches) {
        this.switches = switches;
    }

    public String getKeycaps() {
        return keycaps;
    }

    public void setKeycaps(String keycaps) {
        this.keycaps = keycaps;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getBildUrl() {
        return bildUrl;
    }

    public void setBildUrl(String bildUrl) {
        this.bildUrl = bildUrl;
    }

    public byte[] getBild() {
        return bild;
    }

    public void setBild(byte[] bild) {
        this.bild = bild;
    }
}
