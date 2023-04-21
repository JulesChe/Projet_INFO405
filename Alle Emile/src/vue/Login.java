package vue;

import javax.swing.*;

import controller.ConnexionListener;
import model.ConnexionUtilisateur;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;



public class Login extends JFrame {

    JLabel auth;
    JLabel pseudo;
    JLabel password;
    JTextField tpseudo;
    JPasswordField tpass;
    JButton button;
    Font font = new Font(Font.SANS_SERIF, Font.BOLD, 18);
    Font fb = new Font(Font.SANS_SERIF, Font.BOLD, 12);
    Font fauth = new Font(Font.SANS_SERIF, Font.BOLD, 28);

    public Login() {
        auth = new JLabel("Allez Emile | Connexion");
        auth.setBounds(50, 10, 400, 70);
        auth.setFont(fauth);
        JPanel panel = new JPanel(null);
        panel.add(auth);
        pseudo = new JLabel("Nom");
        pseudo.setFont(font);
        password = new JLabel("Password");
        password.setFont(font);
        tpseudo = new JTextField();
        tpass = new JPasswordField();
        button = new JButton("Connecter");
        button.setFont(fb);
        pseudo.setBounds(50, 100, 90, 40);
        password.setBounds(50, 200, 90, 40);
        tpseudo.setBounds(150, 100, 170, 40);
        tpass.setBounds(150, 200, 170, 40);
        button.setBounds(190, 250, 100, 40);
        button.setBackground(Color.white);
        button.setForeground(Color.blue);
        panel.add(pseudo);
        panel.add(password);
        panel.add(tpseudo);
        panel.add(tpass);
        panel.add(button);
        button.addActionListener(new ConnexionListener(tpseudo,tpass));

        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setSize(600, 400);
        this.setContentPane(panel);
    }

    public static void main(String[] args) {
        Login login = new Login();
    }



    public JTextField getTpseudo() {
        return tpseudo;
    }

    public JPasswordField getTpass() {
        return tpass;
    }


}