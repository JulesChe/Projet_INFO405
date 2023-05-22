package model;

import java.sql.*;

public class Indisponibilite {

    private int id;
    private String motif;
    private Date debut;
    private Date fin;
    private int gardien;

    public Indisponibilite(String motif,Date debut,Date fin,int gardien) {
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

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public int getGardien() {
        return gardien;
    }

    public void setGardien(int gardien) {
        this.gardien = gardien;
    }

    public String toString() {
        return
                "  motif : '" + motif + '\'' +
                ", debut : " + debut +
                ", fin : " + fin
               ;
    }


}
