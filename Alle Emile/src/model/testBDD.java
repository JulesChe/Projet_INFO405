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

        Logistique patrick = new Logistique("Patrick", 3);


        patrick.ajoutGymnase("Saint Gymnase", "23h");

     /*Planning test = new Planning();

        test.getAllCreneaux();
        System.out.println(test.toStringCreneaux());


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
