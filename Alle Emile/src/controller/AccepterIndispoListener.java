package controller;

import model.Indisponibilite;
import model.Logistique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccepterIndispoListener implements ActionListener {

    private Indisponibilite indisponibilite;
    public AccepterIndispoListener(Indisponibilite indisponibilite) {

        this.indisponibilite = indisponibilite;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Logistique p = new Logistique();

        System.out.println(indisponibilite);

        p.accepterIndispo(indisponibilite);

    }
}
