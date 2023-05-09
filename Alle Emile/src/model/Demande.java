package model;

import crud.CrudDemandeDAO;
import crud.CrudGardienDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Demande {
    // ATTRIBUTS
    private Association a;
    private Creneau c;

    // CONSTRUCTEUR
    public Demande(Association a, Creneau c) {
        this.a = a;
        this.c = c;
    }

    public Demande(){

    }

    public int getIdCreneau(Creneau c){

        int id = -1;
        Connection connection = null;
        try {

            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Etablir la connexion avec la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/allezemile", "allezemile", "nT7");
            System.out.println("Connexion établie avec succès.");

            // Créer un objet UtilisateurDAO
            CrudDemandeDAO demandeDAO = new CrudDemandeDAO(connection);

            // Ajouter un creneau

            id = demandeDAO.getIdCreneau(c);



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
}