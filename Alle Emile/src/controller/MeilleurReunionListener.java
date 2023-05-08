package controller;

import model.Logistique;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.HashMap;

public class MeilleurReunionListener implements ActionListener {

    private JPanel reunionPanel;
    private JTextField dateReu;

    public MeilleurReunionListener(JTextField dateReu, JPanel reunionPanel) {

        this.dateReu = dateReu;

        this.reunionPanel = reunionPanel;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Logistique patrick = new Logistique();
        String jour = dateReu.getText();

        try {
            HashMap<String, Integer> hashmap = patrick.getMeilleurDispo(jour);

            // Créer un tableau bidimensionnel pour stocker les données
            String[][] data = new String[hashmap.size()][2];
            int i = 0;
            for (HashMap.Entry<String, Integer> entry : hashmap.entrySet()) {
                data[i][0] = entry.getKey();
                data[i][1] = String.valueOf(entry.getValue());
                i++;
            }

            // Créer un tableau avec des titres de colonne
            String[] columnNames = {"Heure", "Nombre de personne"};

            // Créer une nouvelle JTable avec les données et les titres de colonne
            JTable table = new JTable(data, columnNames);

            // Ajouter la table à un JScrollPane pour permettre le défilement
            JScrollPane scrollPane = new JScrollPane(table);

            // Ajouter le JScrollPane à la JPanel
            reunionPanel.add(scrollPane, BorderLayout.CENTER);

            // Mettre à jour l'interface graphique pour refléter les changements
            reunionPanel.revalidate();
            reunionPanel.repaint();

        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }
}
