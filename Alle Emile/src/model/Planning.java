package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import crud.CrudPlanningDAO;

public class Planning {
    // ATTRIBUTS
    public ArrayList<Creneau> listeCreneaux;

    // CONSTRUCTEUR
    public Planning() {
        ArrayList<Creneau> listeCreneaux = new ArrayList<Creneau>();
    }

    // METHODES
    public void getAllCreneaux(){
        Connection connection = null;
        try {
            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Etablir la connexion avec la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/allezemile", "allezemile", "nT7");
            System.out.println("Connexion établie avec succès.");

            // Créer un objet UtilisateurDAO
            CrudPlanningDAO utilisateurDAO = new CrudPlanningDAO(connection);

            // Récupérer tous les créneaux
            ArrayList<Creneau> listeCreneauxBis = utilisateurDAO.getCreneauxFromTable(connection,"creneau");
            listeCreneaux = listeCreneauxBis;
            System.out.println("Créneaux récupérer avec succès avec succès.");


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

    public String toStringCreneaux() {
        String res = "";
        for (Creneau creneau : listeCreneaux) {
            res = res + creneau.getDateDebut();
            res = res + " ";
            res = res + creneau.getDateFin();
            res = res + "\n";
        }
        return res;
    }
}
