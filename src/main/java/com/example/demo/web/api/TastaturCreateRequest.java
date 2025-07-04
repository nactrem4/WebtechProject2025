package com.example.demo.web.api;

public class TastaturCreateRequest {
    private String tastaturName;
    private String modell;
    private String switches;
    private String keycaps;
    private String beschreibung;
    private String bildUrl;

    private String bildBase64;

    public TastaturCreateRequest(String tastaturName, String modell, String switches, String keycaps, String beschreibung, String bildUrl) {
        this.tastaturName = tastaturName;
        this.modell = modell;
        this.switches = switches;
        this.keycaps = keycaps;
        this.beschreibung = beschreibung;
        this.bildUrl = bildUrl;
    }

    public TastaturCreateRequest() {
    }

    public String getTastaturName() {
        return tastaturName;
    }

    public void setTastaturName(String tastaturname) {
        this.tastaturName = tastaturname;
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

    public String getBildBase64() {
        return bildBase64;
    }

    public void setBildBase64(String bildBase64) {
        this.bildBase64 = bildBase64;
    }
}