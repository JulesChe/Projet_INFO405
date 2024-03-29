package model;

import crud.CrudCreneauDAO;
import crud.CrudUtilisateurDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Arrays;


public class Utilisateur {
    // ATTRIBUTS
    private int id;
    private String nom;
    private int niveau;
    private String prenom;
    private String mdp;


    private static final ArrayList<String> tabJour = new ArrayList<String>(Arrays.asList("08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00"));

    private ArrayList<Creneau> indisponibilites;


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

    public ArrayList<Creneau> getIndisponibilites() {
        return indisponibilites;
    }

    public void setIndisponibilites(ArrayList<Creneau> indisponibilites) {
        this.indisponibilites = sortCreneaux(indisponibilites);
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

    public ArrayList<String> getTabJour(){ return tabJour; }

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

    public HashMap<Integer,ArrayList<String>> selectAllUtilisateurs(){
        HashMap<Integer,ArrayList<String>> liste = new HashMap<>();
        Connection connection = null;
        try {
            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Etablir la connexion avec la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/allezemile", "allezemile", "nT7");
            System.out.println("Connexion établie avec succès.");
            // Créer un objet CreneauDAO
            CrudUtilisateurDAO utilisateurDAO = new CrudUtilisateurDAO(connection);
            liste = utilisateurDAO.selectAll();
        } catch (ClassNotFoundException e) {
            System.err.println("Pilote JDBC introuvable.");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
                }
            }
        }
        return liste;
    }



}


