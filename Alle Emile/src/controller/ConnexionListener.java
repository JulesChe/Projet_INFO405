package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.Association;
import model.ConnexionUtilisateur;
import model.Utilisateur;
import model.WeeklyAgendaModel;
import ressource.PasswordHash;
import vue.AgendaVueFinal;
import vue.Login;
import vue.WeeklyAgendaView;

public class ConnexionListener implements ActionListener{

    private Login loginView;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection connection = null;
    private JTextField tpseudo;
    private JPasswordField tpass;


    public ConnexionListener(JTextField tpseudo, JPasswordField tpass, Login loginView) {
        this.tpseudo = tpseudo;
        this.tpass = tpass;
        this.loginView = loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!tpseudo.getText().isEmpty() || !tpass.getText().isEmpty()) {
            connection = ConnexionUtilisateur.getConnect();
            try {
                ps = connection.prepareStatement("SELECT nom,mdp,grade FROM comptePersonnel WHERE nom=?");
                ps.setString(1, tpseudo.getText());
                rs = ps.executeQuery();

                while (rs.next()) {
                    String hashMdp = rs.getString("mdp");
                    if(PasswordHash.isPasswordValid(tpass.getText(),hashMdp)){
                        Utilisateur utilisateur = new Utilisateur(tpseudo.getText(),"prenom",tpass.getText(),rs.getInt("grade"));
                        WeeklyAgendaModel modele = new WeeklyAgendaModel(null,utilisateur);

                        // Creating an instance of AgendaVueFinal instead of WeeklyAgendaView
                        WeeklyAgendaController controller = new WeeklyAgendaController(modele);
                        AgendaVueFinal agendaVue = new AgendaVueFinal(modele, controller);
                        agendaVue.showView();

                        loginView.frame.dispose();
                    }

                    else {
                        JOptionPane.showMessageDialog(null, "Connexion refusée!");
                    }
                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (PropertyVetoException | ParseException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Erreur de saisir!");
        }
        try {
            connection.close();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }

}