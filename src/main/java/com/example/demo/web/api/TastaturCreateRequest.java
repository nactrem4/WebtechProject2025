package com.example.demo.web.api;

public class TastaturCreateRequest {
    private String tastaturname;
    private String modell;
    private String switches;
    private String keycaps;

    public TastaturCreateRequest( String tastaturname, String modell, String switches, String keycaps) {
        this.tastaturname = tastaturname;
        this.modell = modell;
        this.switches = switches;
        this.keycaps = keycaps;
    }

    public String getTastaturName() {
        return tastaturname;
    }

    public void setTastaturName(String name) {
        this.tastaturname = name;
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
}

