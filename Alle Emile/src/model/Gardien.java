package model;
import crud.CrudCompteAssoDAO;
import crud.CrudCreneauDAO;
import crud.CrudGardienDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    public static boolean isBetween8and20(String dateString) {
        LocalDateTime date = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        int hour = date.getHour();
        return hour >= 8 && hour < 20;
    }

    public ArrayList<Creneau> IndispoVac(ArrayList<Creneau> listeCreneaux){
        ArrayList<Creneau> res = new ArrayList<Creneau>();
        for (Creneau c : listeCreneaux){
            if (this.isBetween8and20(c.getDateDebut())){
                res.add(c);
            }
        }
        return res;
    }

    public ArrayList<Creneau> IndispoPersoFinal(int id) throws ParseException {
        ArrayList<Creneau> res = new ArrayList<Creneau>();
        res = this.IndispoVac(this.tabIndispo(this.getIndispo(id)));
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

    public Map<Integer, ArrayList<Creneau>> getCreneauxGardien() {
        Map<Integer, ArrayList<Creneau>> gardiensCreneaux = null;
        Connection connection = null;
        try {
            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Etablir la connexion avec la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/allezemile", "allezemile", "nT7");
            System.out.println("Connexion établie avec succès.");
            // Créer un objet CreneauDAO
            CrudCreneauDAO creneauDAO = new CrudCreneauDAO(connection);
            gardiensCreneaux = creneauDAO.getAllGardiensCreneaux();
        } catch (ClassNotFoundException e) {
            System.err.println("Pilote JDBC introuvable.");
        } catch (SQLException e) {
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
                }
            }
        }
        return gardiensCreneaux;
    }

    public boolean memeJour(String date1, String date2) {
        // Conversion des chaînes de caractères en objets LocalDateTime
        LocalDateTime dateTime1 = LocalDateTime.parse(date1, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        LocalDateTime dateTime2 = LocalDateTime.parse(date2, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));

        // Vérification si les deux dates ont le même jour
        return dateTime1.toLocalDate().isEqual(dateTime2.toLocalDate());
    }

    public ArrayList<String> getCreneauxLibres(String jour) {
        ArrayList<Creneau> creneaux = (ArrayList<Creneau>) this.getIndisponibilites();
        ArrayList<String> creneauxLibres = new ArrayList<String>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

        for (LocalTime heure = LocalTime.of(8, 0); heure.isBefore(LocalTime.of(20, 0)); heure = heure.plusHours(1)) {
            LocalDateTime creneauDebut = LocalDateTime.parse(jour + " " + heure.toString(), formatter);
            LocalDateTime creneauFin = creneauDebut.plusHours(1);

            boolean creneauLibre = true;
            for (Creneau c : creneaux) {
                LocalDateTime cDebut = LocalDateTime.parse(c.getDateDebut(), formatter);
                LocalDateTime cFin = LocalDateTime.parse(c.getDateFin(), formatter);
                if (cDebut.isBefore(creneauFin) && cFin.isAfter(creneauDebut)) {
                    creneauLibre = false;
                    break;
                }
            }

            if (creneauLibre) {
                creneauxLibres.add(formatter.format(creneauDebut));
            }
        }

        return creneauxLibres;
    }



}
