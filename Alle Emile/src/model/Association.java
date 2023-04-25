package model;
import crud.CrudCompteAssoDAO;
import crud.CrudCreneauDAO;
import crud.CrudDemandeDAO;
import crud.CrudPlanningDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Association{
    // ATTRIBUTS
    private int nbLicence;
    private ArrayList<Creneau> listeCreneaux; // liste des créneaux de l'utilisateur

    private String sport;
    private String mdp;
    private String nom;

    // CONSTRUCTEUR
    public Association(String nom, String mdp) {
        this.nom = nom;
        this.mdp = mdp;
        ArrayList<Creneau> listeCreneaux = new ArrayList<Creneau>();
    }
    public Association(String nom) {
        this.nom = nom;
        this.mdp = "";
        ArrayList<Creneau> listeCreneaux = new ArrayList<Creneau>();
    }


    // METHODES

    public void setNbLicence(int nb) {
        nbLicence = nb;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setListeCreaneau(ArrayList<Creneau> listeCreneaux) {
        this.listeCreneaux = listeCreneaux;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }


    public void demandeCreneau(String dateDebut, String dateFin) {

        Connection connection = null;
        try {

            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Etablir la connexion avec la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/allezemile", "allezemile", "nT7");
            System.out.println("Connexion établie avec succès.");

            // Créer un objet UtilisateurDAO
            CrudDemandeDAO utilisateurDAO = new CrudDemandeDAO(connection);

            // Ajouter un creneau
            Creneau creneau1 = new Creneau(dateDebut,dateFin);
            utilisateurDAO.demande(creneau1);
            System.out.println("Créneau ajouté avec succès.");


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
    }


    /*public Gardien getGardien(Creneau c) {

    }

    public void demande(Creneau c){

    }
    */

    public ArrayList<Association> getAllAsso() {
        Connection connection = null;
        ArrayList<Association> res = null;
        try {
            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Etablir la connexion avec la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/allezemile", "allezemile", "nT7");
            System.out.println("Connexion établie avec succès.");

            // Créer un objet UtilisateurDAO
            CrudCompteAssoDAO assoDAO = new CrudCompteAssoDAO(connection);

            // Récupérer tous les créneaux
            res = assoDAO.getAssoFromTable(connection);

            System.out.println("Association récupérer avec succès.");


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

        return res;

    }



}
