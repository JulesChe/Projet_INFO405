package controller;

import model.Association;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DemandeCreneauListener implements ActionListener {


    private Association associationLog;

    private JTextField dateDebutText, dateFinText;

    public DemandeCreneauListener(Association associationLog,JTextField dateDebutText,JTextField dateFinText) {
        this.associationLog = associationLog;
        this.dateDebutText = dateDebutText;
        this.dateFinText = dateFinText;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String dateDebut = dateDebutText.getText();
        String dateFin = dateFinText.getText();

        associationLog.demandeCreneau(dateDebut,dateFin);
        dateDebutText.setText("");
        dateFinText.setText("");
    }
}
