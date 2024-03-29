package model;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class    Creneau {
    // ATTRIBUTS
    private String dateDebut;
    private String dateFin;
    private String asso;



    private int id;

    private int id_gardien;

    // CONSTRUCTEUR
    public Creneau(String dateDebut, String dateFin) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Creneau() {

    }

    public Creneau(String dateDebut, String dateFin, String asso) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.asso = asso;
    }

    // METHODES
    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateDebut(String dateDebut) throws IllegalArgumentException {
        if (estValideQuartdheure(dateDebut)) {
            this.dateDebut = dateDebut;
        } else {
            throw new IllegalArgumentException("Invalid time. Time must be on quarter hours.");
        }
    }

    public void setDateFin(String dateFin) throws IllegalArgumentException {
        if (estValideQuartdheure(dateFin)) {
            this.dateFin = dateFin;
        } else {
            throw new IllegalArgumentException("Invalid time. Time must be on quarter hours.");
        }
    }

    public String getDayOfWeek(String dateDebut) {
        // Créer un objet LocalDateTime en parsant la chaîne de caractères
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateDebut, formatter);

        // Récupérer le jour de la semaine sous forme d'une chaîne de caractères
        return dateTime.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
    }


    public String getDayOfWeekSec() {
        // Créer un objet LocalDateTime en parsant la chaîne de caractères
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateDebut, formatter);

        // Récupérer le jour de la semaine sous forme d'une chaîne de caractères
        return dateTime.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
    }

    private boolean estValideQuartdheure(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        int minutes = dateTime.getMinute();
        return minutes % 15 == 0;
    }

    public String formatDate(String inputDate) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.FRENCH);
        LocalDate date = LocalDate.parse(inputDate, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return date.format(outputFormatter);
    }

    public int nbJour(String jour){
        int res = -1;
        if(jour.equals("lundi")){
            res = 0;
        }
        else if (jour.equals("mardi")){
            res = 1;
        }
        else if (jour.equals("mercredi")){
            res = 2;
        }
        else if (jour.equals("jeudi")){
            res = 3;
        }
        else if (jour.equals("vendredi")){
            res = 4;
        }
        else if (jour.equals("samedi")){
            res = 5;
        }
        else if (jour.equals("dimanche")){
            res = 6;
        }
        return res;

    }

    public String getAsso() {
        return asso;
    }

    public static String getDebutSemaine() {
        LocalDate aujourdHui = LocalDate.now();
        LocalDate lundi = aujourdHui.with(DayOfWeek.MONDAY);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return lundi.format(formatter);
    }

    public String getDebutSemaine(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate inputDate = LocalDate.parse(date, formatter);
        LocalDate lundi = inputDate.with(DayOfWeek.MONDAY);
        return lundi.format(formatter);
    }

    public static String ajouter7Jours(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        LocalDate localDatePlus7Jours = localDate.plusDays(7);
        return localDatePlus7Jours.format(formatter);
    }

    public String ajouterUnJour(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Calendar c = Calendar.getInstance();
        try {
            Date date = sdf.parse(dateStr);
            c.setTime(date);
            c.add(Calendar.DATE, 1);  // Ajout de 1 jour
            dateStr = sdf.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_gardien(int id_gardien) {
        this.id_gardien = id_gardien;
    }

    public String transformDate(String dateString) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf1.parse(dateString);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String newDateString = sdf2.format(date);
        return newDateString;
    }

    public ArrayList<Creneau> transformCreneau(ArrayList<Creneau> lc) throws ParseException {
        ArrayList<Creneau> res = new ArrayList<>();
        for (Creneau c : lc){
            Creneau c1 = new Creneau(this.transformDate(c.getDateDebut()),this.transformDate(c.getDateFin()),c.getAsso());
            res.add(c1);
        }
        return res;
    }

    @Override
    public String toString() {
        return
                " Association : " + asso +
                " Debut : " + dateDebut +
                " Fin : " + dateFin +
                "\n";
    }
}