package model;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;

import crud.CrudCompteAssoDAO;
import crud.CrudCreneauDAO;

import java.util.Date;
import java.util.Scanner;

public class testBDD extends JFrame{

    private static javax.swing.JList<String> list;

    private static void affiche(String message) {
        System.out.println(message);
    }

    private static void arret(String message) {
        System.err.println(message);
        System.exit(99);
    }

    public static void main(String args[]) throws ClassNotFoundException, SQLException, ParseException {

        Logistique patrick = new Logistique("Logistique","Patrick","testmdp", 3);
        /*Association asso = new Association("test","mdp");

        asso.demandeCreneau("2023/04/20 16:00:00","2023/04/20 18:00:00");
        asso.demandeCreneau("2023/04/21 14:00:00","2023/04/20 16:00:00");
        asso.demandeCreneau("2023/04/22 09:00:00","2023/04/20 11:00:00");*/

        //patrick.accepterCreneau(1);
        //patrick.pasAccepterCreneau(3);

        Creneau c = new Creneau("2023/05/02 16:00","2023/05/02 18:00");
        //System.out.print(c.getDateDebut());

        //patrick.ajoutCreneau("2023/05/02 16:00","2023/05/02 18:00");
        //patrick.accepterCreneau(15);
        //System.out.println(patrick.getIDAsso("AS FOOT"));



        /*for (Map.Entry<Integer, List<Creneau>> entry : gardiensCreneaux.entrySet()) {
            System.out.println("Gardien ID: " + entry.getKey());
            System.out.println("Créneaux :");
            for (Creneau creneau : entry.getValue()) {
                System.out.println(creneau);
            }
        }*/

        //String lundi = c.getDebutSemaine();
        //Planning test = new Planning();

        //test.getSemaine("2023/04/24");
        //System.out.println("créneaux : "+test.toStringCreneaux());


        //System.out.println(c.getDebutSemaine());
/*

        //Scanner scanner = new Scanner(System.in);

        // Récupération des dates de début et fin ( bien rentrer SANS guillemet )
        System.out.print("Veuillez saisir la date de début avec ce format : yyyy/MM/dd HH:mm:ss");
        String dateD = scanner.nextLine();
        System.out.print("Veuillez saisir la date de Fin avec ce format : yyyy/MM/dd HH:mm:ss");
        String dateF = scanner.nextLine();



        patrick.ajoutCreneau(dateD,dateF); */

        //patrick.ajoutCreneau("GPROUTE", "tata");



        Gardien emilien = new Gardien("TEST", "test","vzera",1);

        Map<Integer, ArrayList<Creneau>> gardiensCreneaux = emilien.getCreneauxGardien();

        ArrayList<Creneau> lc = emilien.IndispoPersoFinal(1);

        for (Creneau creneau : lc) {

            gardiensCreneaux.get(1).add(creneau);

        }


        //emilien.setIndisponibilites(gardiensCreneaux.get(1));

        //System.out.print(emilien.memeJour("2023/05/05 16:00", "2023/05/10 18:00"));

        //System.out.print(emilien.getIndisponibilites());

        //System.out.print(emilien.getCreneauxLibres("2023/05/07"));

        System.out.print(patrick.getMostFrequentHours(patrick.getMeilleurCreneau("2023/05/07")));


    }

}
