package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Utilisateur {
    // ATTRIBUTS
    private int id;
    private String nom;
    private int niveau;
    private String prenom;
    private String mdp;

    private List<Creneau> indisponibilites;


    // CONSTRUCTEURS
    public Utilisateur(String nom,String prenom,String mdp, int niveau){
        this.nom = nom;
        this.niveau = niveau;
        this.prenom= prenom;
        this.mdp = mdp;
    }

    public Utilisateur(){

    }

    // METHODE

    public List<Creneau> getIndisponibilites() {
        return indisponibilites;
    }

    public void setIndisponibilites(ArrayList<Creneau> indisponibilites) {
        this.indisponibilites = indisponibilites;
    }

    public ArrayList<Creneau> sortCreneaux(ArrayList<Creneau> creneaux) {
        ArrayList<LocalDateTime> dates = new ArrayList<LocalDateTime>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

        // Convertir chaque date de début et de fin en objet LocalDateTime et les stocker dans une liste
        for (Creneau creneau : creneaux) {
            LocalDateTime dateDebut = LocalDateTime.parse(creneau.getDateDebut(), formatter);
            LocalDateTime dateFin = LocalDateTime.parse(creneau.getDateFin(), formatter);
            dates.add(dateDebut);
            dates.add(dateFin);
        }

        // Trier les dates en ordre chronologique
        Collections.sort(dates, new Comparator<LocalDateTime>() {
            public int compare(LocalDateTime date1, LocalDateTime date2) {
                return date1.compareTo(date2);
            }
        });

        // Construire une nouvelle liste de créneaux triés
        ArrayList<Creneau> creneauxTries = new ArrayList<Creneau>();
        for (int i = 0; i < dates.size() - 1; i++) {
            LocalDateTime dateDebut = dates.get(i);
            LocalDateTime dateFin = dates.get(i + 1);
            for (Creneau creneau : creneaux) {
                LocalDateTime creneauDateDebut = LocalDateTime.parse(creneau.getDateDebut(), formatter);
                LocalDateTime creneauDateFin = LocalDateTime.parse(creneau.getDateFin(), formatter);
                if (creneauDateDebut.equals(dateDebut) && creneauDateFin.equals(dateFin)) {
                    creneauxTries.add(creneau);
                    break;
                }
            }
        }

        return creneauxTries;
    }

    public void addIndisponibilite(Creneau creneau) {
        this.indisponibilites.add(creneau);
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNiveau() {
        return niveau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }


}
