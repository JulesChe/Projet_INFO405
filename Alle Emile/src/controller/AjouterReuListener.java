package controller;

import model.Gardien;
import model.Logistique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AjouterReuListener implements ActionListener {
    private String creneau;

    public AjouterReuListener(String creneau) {
        this.creneau = creneau;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Logistique patrick = new Logistique();
        Gardien emilien = new Gardien();
        String creneauFin = patrick.addOneHour(creneau);
        patrick.ajoutCreneau(creneau,creneauFin,7,null);
    }
}
