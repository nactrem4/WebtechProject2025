package com.example.demo.persistence;

import jakarta.persistence.*;

@Entity(name = "tastaturen")
public class TastaturEntität {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tastaturName")
    private String tastaturName;

    @Column(name = "modell")
    private String modell;

    @Column(name = "switches")
    private String switches;

    @Column(name = "keycaps")
    private String keycaps;

    @Column(name = "beschreibung")
    private String beschreibung;

    @Column(name = "bildUrl")
    private String bildUrl;

    @Lob
    @Column(name = "bild", columnDefinition = "BYTEA")
    private byte[] bild;

    public TastaturEntität(String tastaturName, String modell, String switches, String keycaps, String beschreibung, String bildUrl, byte[] bild) {
        this.tastaturName = tastaturName;
        this.modell = modell;
        this.switches = switches;
        this.keycaps = keycaps;
        this.beschreibung = beschreibung;
        this.bildUrl = bildUrl;
        this.bild = bild;
    }

    public TastaturEntität() {

    }

    public Long getId() {
        return id;
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
