package model;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

public class Creneau {
    // ATTRIBUTS
    private String dateDebut;
    private String dateFin;
    private int id;

    // CONSTRUCTEUR
    public Creneau(String dateDebut, String dateFin) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public int getId() {
        return id;
    }

    public Creneau(String dateDebut, String dateFin, int id) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.id = id;
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

    public String getDayOfWeek() {
        // Créer un objet LocalDateTime en parsant la chaîne de caractères
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateDebut, formatter);

        // Récupérer le jour de la semaine sous forme d'une chaîne de caractères
        return dateTime.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
    }
}