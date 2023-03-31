package model;
import java.util.Date;

public class Creneau {
    // ATTRIBUTS
    private String dateDebut;
    private String dateFin;

    // CONSTRUCTEUR
    public Creneau(String dateDebut, String dateFin) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    // METHODES
    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }


}