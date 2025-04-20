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

    public TastaturEntität(String tastaturname, String modell, String switches, String keycaps) {
        this.tastaturname = tastaturname;
        this.modell = modell;
        this.switches = switches;
        this.keycaps = keycaps;
    }

    protected TastaturEntität() {
    }

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
}
