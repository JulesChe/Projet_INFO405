package controller;

import model.Indisponibilite;
import model.Logistique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefuserIndispoListener implements ActionListener {

    private Indisponibilite indisponibilite;
    public RefuserIndispoListener(Indisponibilite indisponibilite) {
        this.indisponibilite = indisponibilite;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Logistique patrick = new Logistique();

        patrick.refuserIndispo(indisponibilite);

    }
}
