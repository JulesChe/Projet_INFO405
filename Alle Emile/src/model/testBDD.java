package model;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;

import crud.CrudCompteAssoDAO;

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

    public static void main(String args[]) throws ClassNotFoundException, SQLException {

        Logistique patrick = new Logistique("Logistique","Patrick","testmdp", 3);
        /*Association asso = new Association("test","mdp");

        asso.demandeCreneau("2023/04/20 16:00:00","2023/04/20 18:00:00");
        asso.demandeCreneau("2023/04/21 14:00:00","2023/04/20 16:00:00");
        asso.demandeCreneau("2023/04/22 09:00:00","2023/04/20 11:00:00");*/

        //patrick.accepterCreneau(1);
        //patrick.pasAccepterCreneau(3);
        Creneau c = new Creneau("2023/04/25 16:00","2023/04/25 18:00");
        //String lundi = c.getDebutSemaine();
        Planning test = new Planning();

        test.getSemaine("2023/04/24");
        System.out.println("créneaux : "+test.toStringCreneaux());


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
    }
}
