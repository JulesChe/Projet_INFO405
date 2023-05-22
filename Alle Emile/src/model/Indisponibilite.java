package model;

import java.sql.*;

public class Indisponibilite {

    private int id;
    private String motif;
    private String debut;
    private String fin;
    private int gardien;

    public Indisponibilite(String motif,String debut,String fin,int gardien) {
        this.motif = motif;
        this.debut = debut;
        this.fin = fin;
        this.gardien = gardien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getDebut() {
        return debut;
    }

    public void setDebut(String debut) {
        this.debut = debut;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public int getGardien() {
        return gardien;
    }

    public void setGardien(int gardien) {
        this.gardien = gardien;
    }
}
