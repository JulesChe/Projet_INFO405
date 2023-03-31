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

import model.ConnexionUtilisateur;

public class ConnexionListener implements ActionListener{

    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection connection = null;
    private JTextField tpseudo;
    private JPasswordField tpass;

    public ConnexionListener(JTextField tpseudo, JPasswordField tpass) {
        this.tpseudo = tpseudo;
        this.tpass = tpass;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!tpseudo.getText().isEmpty() || !tpass.getText().isEmpty()) {
            connection = ConnexionUtilisateur.getConnect();
            try {
                ps = connection.prepareStatement("SELECT nom FROM comptePersonnel WHERE nom=? AND mdp =?");
                ps.setString(1, tpseudo.getText());
                ps.setString(2, tpass.getText());
                rs = ps.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Connexion réussi");
                } else {
                    JOptionPane.showMessageDialog(null, "Connexion refusée!");
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