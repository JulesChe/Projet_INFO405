package model;
import crud.CrudCompteAssoDAO;
import crud.CrudGardienDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Gardien extends Utilisateur{
    // ATTRIBUTS

    // CONSTRUCTEUR
    public Gardien(String nom,String prenom,String mdp, int niveau) {
        super(nom,prenom,mdp,niveau);
    }

    // METHODES


    @Override
    public String getNom() {
        return super.getNom();
    }

    @Override
    public String getPrenom() {
        return super.getPrenom();
    }

    public int getID(){
        int id = -1;
        Connection connection = null;
        try {

            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Etablir la connexion avec la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/allezemile", "allezemile", "nT7");
            System.out.println("Connexion établie avec succès.");



            // Créer un objet UtilisateurDAO
            CrudGardienDAO assoDAO = new CrudGardienDAO(connection);

            // Ajouter un creneau

            id = assoDAO.selectId(this.getNom(),this.getPrenom());

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

    public ArrayList<Creneau> getIndispo(int id){
        ArrayList<Creneau> c = null;
        Connection connection = null;
        try {

            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Etablir la connexion avec la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/allezemile", "allezemile", "nT7");
            System.out.println("Connexion établie avec succès.");



            // Créer un objet UtilisateurDAO
            CrudGardienDAO assoDAO = new CrudGardienDAO(connection);

            // Ajouter un creneau

            c = assoDAO.getIndispo(id);

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

        return c;
    }

    public static String addOneHour(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        Date newDate = calendar.getTime();
        return sdf.format(newDate);
    }



    public ArrayList<Creneau> tabIndispo(ArrayList<Creneau> listeCreneaux) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        ArrayList<Creneau> res = new ArrayList<Creneau>();
        for (Creneau c : listeCreneaux){
            Date debut = sdf.parse(c.transformDate(c.getDateDebut()));
            Date fin = sdf.parse(c.transformDate(c.getDateFin()));
            while (debut.before(fin)) {
                String dateDebut = sdf.format(debut);
                String dateFin = this.addOneHour(debut);
                Creneau c1 = new Creneau(dateDebut,dateFin);
                res.add(c1);
                debut = sdf.parse(dateFin);
            }
        }
        return res;
    }



    public String toStringCreneaux(ArrayList<Creneau> listeCreneaux) {
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
