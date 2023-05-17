package model;
import crud.CrudCompteAssoDAO;
import crud.CrudCreneauDAO;
import crud.CrudGardienDAO;
import crud.CrudUtilisateurDAO;

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

    public Gardien(){

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

    public Map<Integer, ArrayList<Creneau>> getIndispo(){
        Map<Integer, ArrayList<Creneau>> listecreneauPersonnel = null;
        Connection connection = null;
        try {

            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Etablir la connexion avec la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/allezemile", "allezemile", "nT7");
            System.out.println("Connexion établie avec succès.");



            // Créer un objet UtilisateurDAO
            CrudUtilisateurDAO utilisateurDAO = new CrudUtilisateurDAO(connection);

            // Ajouter un creneau

            listecreneauPersonnel = utilisateurDAO.getIndispo();

            System.out.println("ID Association trouvé avec succès.");

            for(Map.Entry mapentry : listecreneauPersonnel.entrySet()){

                listecreneauPersonnel.put((Integer) mapentry.getKey(),tabIndispo((ArrayList<Creneau>) mapentry.getValue()));

            }




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

        return listecreneauPersonnel;
    }

    public String addOneHour(Date date) {
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
            Date debut = sdf.parse(c.getDateDebut());
            Date fin = sdf.parse(c.getDateFin());
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

   /* public ArrayList<Creneau> IndispoPersoFinal(int id) throws ParseException {
        ArrayList<Creneau> res = new ArrayList<Creneau>();
        res = this.IndispoVac(this.tabIndispo(this.getIndispo()));
        return res;
    }*/



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

    public Map<Integer, ArrayList<Creneau>> getCreneauxPersonnel() {
        Map<Integer, ArrayList<Creneau>> personnelCreneaux = null;
        Connection connection = null;
        try {
            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Etablir la connexion avec la base de données
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/allezemile", "allezemile", "nT7");
            System.out.println("Connexion établie avec succès.");
            // Créer un objet CreneauDAO
            CrudCreneauDAO creneauDAO = new CrudCreneauDAO(connection);
            personnelCreneaux = creneauDAO.getAllGardiensCreneaux();
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
        return personnelCreneaux;
    }

    public void setAllIndispoGardien(int idGardien) throws ParseException {

        Map<Integer, ArrayList<Creneau>> gardiensCreneaux = null;
        gardiensCreneaux = this.getCreneauxPersonnel();
        Map<Integer, ArrayList<Creneau>> listeIndispo = this.getIndispo();


        //J'ajoute les indisponibilités en plus des creneaux

        if(listeIndispo.get(idGardien) != null){

            for (Creneau creneau : listeIndispo.get(idGardien)) {

                gardiensCreneaux.get(idGardien).add(creneau);


            }
        }


        if (gardiensCreneaux.get(idGardien)==null) {
        }else{
            this.setIndisponibilites(gardiensCreneaux.get(idGardien));
            this.setId(idGardien);
        }



    }


    public HashMap<Integer, ArrayList<String>> getCreneauxLibres(String jour) {
        ArrayList<Creneau> creneaux = (ArrayList<Creneau>) this.getIndisponibilites();
        ArrayList<String> creneauxLibres = new ArrayList<String>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        if (creneaux == null){
            for(String s : this.getTabJour()){
                creneauxLibres.add(jour+" "+s);
            }
        }else {
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
        }


        HashMap<Integer, ArrayList<String>> res = new HashMap<>();
        res.put(this.getID(),creneauxLibres);
        return res;
    }



}
