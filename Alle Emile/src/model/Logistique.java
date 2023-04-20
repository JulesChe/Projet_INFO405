package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import crud.*;
import ressource.PasswordHash;

public class Logistique extends Utilisateur{
    // ATTRIBUTS

    // CONSTRUCTEUR
    public Logistique(String nom,String prenom,String mdp ,int niveau) {
        super(nom,prenom,mdp,niveau);
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

            // Créer un objet CrudCompteAssoDAO
            CrudCompteAssoDAO associationDAO = new CrudCompteAssoDAO(connection);

            //Hachage du mdp
            mdp = PasswordHash.getHashPassword(mdp);

            // Ajouter une Association
            Association association1 = new Association(nom,mdp);
            associationDAO.create(association1);
            System.out.println("Association ajouté avec succès.");


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


    public void ajoutUtilisateur(String nom,String prenom,String mdp, int niveau) {
        Connection connection = null;
        try {
            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Etablir la connexion avec la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/allezemile", "allezemile", "nT7");
            System.out.println("Connexion établie avec succès.");

            // Créer un objet UtilisateurDAO
            CrudUtilisateurDAO utilisateurDAO = new CrudUtilisateurDAO(connection);

            //Hashage du mot de passe
            mdp = PasswordHash.getHashPassword(mdp);

            // Ajouter un gymnase
            Utilisateur utilisateur1 = new Utilisateur(nom, prenom, mdp, niveau);
            utilisateurDAO.create(utilisateur1);
            System.out.println("Personnel ajouté avec succès.");


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

    public void accepterCreneau(int id) {

        Connection connection = null;
        try {

            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Etablir la connexion avec la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/allezemile", "allezemile", "nT7");
            System.out.println("Connexion établie avec succès.");

            // Créer un objet UtilisateurDAO
            CrudDemandeDAO utilisateurDAO = new CrudDemandeDAO(connection);

            // Accepter un creneau
            utilisateurDAO.accepter(id);
            System.out.println("Créneau accepter avec succès.");


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

    public void pasAccepterCreneau(int id) {

        Connection connection = null;
        try {

            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Etablir la connexion avec la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/allezemile", "allezemile", "nT7");
            System.out.println("Connexion établie avec succès.");

            // Créer un objet UtilisateurDAO
            CrudDemandeDAO utilisateurDAO = new CrudDemandeDAO(connection);

            // Accepter un creneau
            utilisateurDAO.supprimer(id);
            System.out.println("Demande de créneau supprimer avec succès.");


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
