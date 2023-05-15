package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;

import crud.*;
import ressource.PasswordHash;

public class Logistique extends Utilisateur{
    // ATTRIBUTS

    // CONSTRUCTEUR
    public Logistique(String nom,String prenom,String mdp ,int niveau) {
        super(nom,prenom,mdp,niveau);
    }

    public Logistique(){}
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void modifierCreneau(String debut, String fin){

        Connection connection = null;
        try {

            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Etablir la connexion avec la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/allezemile", "allezemile", "nT7");
            System.out.println("Connexion établie avec succès.");

            // Créer un objet CrudCompteAssoDAO
            CrudCreneauDAO creneauDAO = new CrudCreneauDAO(connection);

            // Ajouter une Association
            Creneau creneau = new Creneau(debut,fin);
            creneauDAO.modifier(creneau,debut,fin);
            System.out.println("Creneau supprimer avec succès.");


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
    public void supprimerCreneau(String debut, String fin){

        Connection connection = null;
        try {

            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Etablir la connexion avec la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/allezemile", "allezemile", "nT7");
            System.out.println("Connexion établie avec succès.");

            // Créer un objet CrudCompteAssoDAO
            CrudCreneauDAO creneauDAO = new CrudCreneauDAO(connection);

            // Ajouter une Association
            Creneau creneau = new Creneau(debut,fin);
            creneauDAO.supprimer(creneau);
            System.out.println("Creneau supprimer avec succès.");


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

    public void ajoutCreneau(String dateDebut, String dateFin, int idAsso,String gymnase) {

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
            utilisateurDAO.create(creneau1,idAsso,gymnase);
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

            // Ajouter un utilisateur
            Utilisateur utilisateur1 = new Utilisateur(nom, prenom, mdp, niveau);
            utilisateurDAO.create(utilisateur1);

            utilisateurDAO.setId(utilisateur1);
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

    public int getIDAsso(String nom){
        int id = -1;
        Connection connection = null;
        try {

            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Etablir la connexion avec la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/allezemile", "allezemile", "nT7");
            System.out.println("Connexion établie avec succès.");



            // Créer un objet UtilisateurDAO
            CrudCompteAssoDAO assoDAO = new CrudCompteAssoDAO(connection);

            // Ajouter un creneau

            id = assoDAO.selectId(nom);

            System.out.println("ID Association trouvé avec succès.");


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

        return id;
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

    public ArrayList<Integer> getAllIdPerso() {

        Connection connection = null;
        ArrayList<Integer> resultList = null;
        try {

            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Etablir la connexion avec la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/allezemile", "allezemile", "nT7");
            System.out.println("Connexion établie avec succès.");

            // Créer un objet UtilisateurDAO
            CrudUtilisateurDAO utilisateurDAO = new CrudUtilisateurDAO(connection);

            // Accepter un creneau
            resultList = new ArrayList<>();
            resultList = utilisateurDAO.getIdAllPerso();
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
        return resultList;
    }

   public ArrayList<String> getCreneauAutrePersonnel(String jour) throws ParseException {

        ArrayList<String> res = new ArrayList<>();

        ArrayList<Integer> allIdGardien = this.getAllIdPerso();

        for(int id : allIdGardien){

            Gardien g = new Gardien();

            g.setAllIndispoGardien(id);

            Map<Integer, ArrayList<String>> resInt = g.getCreneauxLibres(jour);

            for(Map.Entry mapentry : resInt.entrySet()){

                for(String s : (ArrayList<String>)mapentry.getValue()){

                    res.add(s);

                }


            }

        }


        return res;
    }

    public ArrayList<String> getCreneauPrioritaire(String jour,ArrayList<Integer> listePrioritaire) throws ParseException {

        ArrayList<String> res = new ArrayList<>();


        for(int id : listePrioritaire){

            Gardien g = new Gardien();

            g.setAllIndispoGardien(id);

            Map<Integer, ArrayList<String>> resInt = g.getCreneauxLibres(jour);

            for(Map.Entry mapentry : resInt.entrySet()){

                for(String s : (ArrayList<String>)mapentry.getValue()){

                    res.add(s);

                }


            }

        }


        return res;
    }

    public static HashMap<ArrayList<String>, Integer> getMostFrequentHours(ArrayList<String> dates, ArrayList<String> autre) {
        HashMap<String, Integer> frequencyMap = new HashMap<>();

        for (String date : dates) {
            String[] parts = date.split(" ");
            String time = parts[1].substring(0, 5);
            frequencyMap.put(time, frequencyMap.getOrDefault(time, 0) + 1);
        }

        HashMap<ArrayList<String>, Integer> result = new HashMap<>();
        int maxFrequency = 0;

        for (String time : autre) {
            int frequency = frequencyMap.getOrDefault(time, 0);

            if (frequency > maxFrequency) {
                maxFrequency = frequency;
                result.clear();
                ArrayList<String> mostFrequentHours = new ArrayList<>();
                mostFrequentHours.add(time);
                result.put(mostFrequentHours, frequency);
            } else if (frequency == maxFrequency) {
                for (ArrayList<String> hours : result.keySet()) {
                    hours.add(time);
                    break; // Ajouté pour éviter l'ajout multiple d'heures
                }
            }
        }

        return result;
    }



    public HashMap<ArrayList<String>, Integer> getMeilleurDispo(String jour, ArrayList<String> listeCreneauPrio, ArrayList<String> listCreneauAutrePersonnel) throws ParseException {

        HashMap<ArrayList<String>, Integer> res = new HashMap<>();

        res = getMostFrequentHours(listeCreneauPrio,listCreneauAutrePersonnel);

        return res;


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
