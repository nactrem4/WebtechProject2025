package com.example.demo.persistence;

import jakarta.persistence.*;

@Entity(name = "tastaturen")
public class TastaturEntität {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tastaturname")
    private String tastaturname;

    @Column(name = "modell")
    private String modell;

    @Column(name = "switches")
    private String switches;

    @Column(name = "keycaps")
    private String keycaps;

    @Column(name = "beschreibung")  // NEU
    private String beschreibung;

    @Column(name = "bild_url")  // NEU
    private String bildUrl;

    // Neuer Konstruktor mit allen Feldern
    public TastaturEntität(String tastaturname, String modell, String switches, String keycaps, String beschreibung, String bildUrl) {
        this.tastaturname = tastaturname;
        this.modell = modell;
        this.switches = switches;
        this.keycaps = keycaps;
        this.beschreibung = beschreibung;
        this.bildUrl = bildUrl;
    }

    protected TastaturEntität() {}

    public Long getId() {
        return id;
    }

    public String getTastaturname() {
        return tastaturname;
    }

    public void setTastaturname(String tastaturname) {
        this.tastaturname = tastaturname;
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
}
