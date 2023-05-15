package controller;

import model.Creneau;
import model.Demande;
import model.Logistique;
import model.Planning;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AccepterDemandeListener implements ActionListener {

    private Object[][] data;
    private JPanel requestPanelB;

    private Planning p;

    public AccepterDemandeListener(Object[][] data, JPanel requestPanelB, Planning p) {
        this.requestPanelB = requestPanelB;
        this.data = data;
        this.p = p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String getId = JOptionPane.showInputDialog(requestPanelB, "Veuillez entrer l'id de la demande à accepter");

        ArrayList<Creneau> liste = p.getListeCreneaux();

        for(int i = 0; i < data.length; i++){

            for(int j = 0; j < data.length; i++){

                int id = Integer.parseInt(getId);

                if(i == id){

                    System.out.println(liste.get(i));

                    Demande demande = new Demande();

                    int accepterCreneauId = demande.getIdCreneau(liste.get(i));

                    Logistique patrick = new Logistique();

                    patrick.accepterCreneau(accepterCreneauId);

                    requestPanelB.repaint();


                }

            }


        }

    }
}
