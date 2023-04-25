package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.Association;
import model.ConnexionUtilisateur;
import model.WeeklyAgendaModel;
import ressource.PasswordHash;
import vue.Login;
import vue.WeeklyAgendaView;

public class ConnexionListener implements ActionListener{

    private Login login;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection connection = null;
    private JTextField tpseudo;
    private JPasswordField tpass;


    public ConnexionListener(JTextField tpseudo, JPasswordField tpass, Login login) {
        this.tpseudo = tpseudo;
        this.tpass = tpass;
        this.login = login;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!tpseudo.getText().isEmpty() || !tpass.getText().isEmpty()) {
            connection = ConnexionUtilisateur.getConnect();
            try {
                ps = connection.prepareStatement("SELECT nom,mdp FROM comptePersonnel WHERE nom=?");
                ps.setString(1, tpseudo.getText());
                rs = ps.executeQuery();

                while (rs.next()) {
                    String hashMdp = rs.getString("mdp");
                    if(PasswordHash.isPasswordValid(tpass.getText(),hashMdp)){
                        JOptionPane.showMessageDialog(null, "Connexion réussi");
                        Association associationTestErr = new Association("testerr","mdp");
                        WeeklyAgendaModel modele = new WeeklyAgendaModel(null);

                        WeeklyAgendaView view = new WeeklyAgendaView(modele);
                        WeeklyAgendaController controller = new WeeklyAgendaController(view, modele);

                        login.dispose();
                        view.frame.setVisible(true);


                        // Créer une instance de WeeklyAgendaApp et lancer l'application

                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Connexion refusée!");
                    }
                }

            } catch (SQLException e1) {
                e1.printStackTrace();
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