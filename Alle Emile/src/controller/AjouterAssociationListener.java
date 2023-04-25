package controller;

import model.Logistique;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AjouterAssociationListener implements ActionListener {

    private JTextField nameField;

    private JPasswordField passwordField;

    private DefaultListModel<String> listModel;

    public AjouterAssociationListener(JTextField nameField,JPasswordField passwordField,DefaultListModel<String> listModel){
        this.nameField = nameField;
        this.passwordField = passwordField;
        this.listModel = listModel;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // Code pour créer l'association avec les valeurs des champs de texte
        String name = nameField.getText();
        String password = new String(passwordField.getPassword());

        // Ajout du nom de la nouvelle association à la liste
        listModel.addElement(name);

        //Ajout dans la BDD
        Logistique patrick = new Logistique();

        patrick.ajoutAssociation(name,password);

        // Réinitialisation des champs de texte
        nameField.setText("");
        passwordField.setText("");
    }
}
