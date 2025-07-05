package com.example.demo.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "tastaturen")
public class TastaturEntität {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tastaturname")
    private String tastaturName;

    private String modell;

    private String switches;

    private String keycaps;

    @Column(length = 1000)
    private String beschreibung;

    @Column(name = "bild", columnDefinition = "bytea")
    private byte[] bild;

    @Column(nullable = false)
    private int upvotes = 0;

    @Column(nullable = false)
    private int downvotes = 0;

    // Getter und Setter

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

    public byte[] getBild() {
        return bild;
    }

    public void setBild(byte[] bild) {
        this.bild = bild;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }
}
