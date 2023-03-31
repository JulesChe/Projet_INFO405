package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import crud.*;

public class Logistique extends Utilisateur{
    // ATTRIBUTS

    // CONSTRUCTEUR
    public Logistique(String nom, int niveau) {
        super(nom,niveau);
    }
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void ajoutAssociation(String nom, String mdp) {

        Connection connection = null;
        try {
            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Etablir la connexion avec la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/allezemile", "allezemile", "nT7");
            System.out.println("Connexion établie avec succès.");

            // Créer un objet UtilisateurDAO
            CrudCompteAssoDAO utilisateurDAO = new CrudCompteAssoDAO(connection);

            // Ajouter un utilisateur
            Association utilisateur1 = new Association(nom,mdp);
            utilisateurDAO.create(utilisateur1);
            System.out.println("Utilisateur ajouté avec succès.");


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

    public void ajoutCreneau(String dateDebut, String dateFin) {

        Connection connection = null;
        try {

            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Etablir la connexion avec la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/allezemile", "allezemile", "nT7");
            System.out.println("Connexion établie avec succès.");

            // Créer un objet UtilisateurDAO
            CrudCreneauDAO utilisateurDAO = new CrudCreneauDAO(connection);

            // Ajouter un creneau
            Creneau creneau1 = new Creneau(dateDebut,dateFin);
            utilisateurDAO.create(creneau1);
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

    public void ajoutGymnase(String nom, String lieux) {

        Connection connection = null;
        try {
            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Etablir la connexion avec la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/allezemile", "allezemile", "nT7");
            System.out.println("Connexion établie avec succès.");

            // Créer un objet UtilisateurDAO
            CrudGymnaseDAO utilisateurDAO = new CrudGymnaseDAO(connection);

            // Ajouter un gymnase
            Gymnase gymnase1 = new Gymnase(nom,lieux);
            utilisateurDAO.create(gymnase1);
            System.out.println("Gymnase ajouté avec succès.");


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



    // METHODES
	/*
	public void ajouter(Creneau c){

	}

	public void supprimer(Creneau c){

	}

	public Planning getPlanningGardien(Gardien g){

	}
	 */
}
