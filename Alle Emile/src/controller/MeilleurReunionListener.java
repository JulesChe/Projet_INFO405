package controller;

import model.Logistique;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MeilleurReunionListener implements ActionListener {

    private JPanel reunionPanel;
    private JTextField dateReu;

    public MeilleurReunionListener(JTextField dateReu, JPanel reunionPanel) {

        this.dateReu = dateReu;

        this.reunionPanel = reunionPanel;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
    /*
        Logistique patrick = new Logistique();
        String jour = dateReu.getText();

        try {
            HashMap<String, Integer> hashmap = patrick.getMeilleurDispo(jour);

            // Stocker les données dans un tableau ArrayList
            ArrayList<String[]> dataList = new ArrayList<>();
            for (HashMap.Entry<String, Integer> entry : hashmap.entrySet()) {
                dataList.add(new String[]{entry.getKey(), String.valueOf(entry.getValue())});
            }

            // Trier les données par ordre croissant en utilisant un comparateur personnalisé pour le format hh:mm
            Collections.sort(dataList, new Comparator<String[]>() {
                @Override
                public int compare(String[] o1, String[] o2) {
                    DateFormat format = new SimpleDateFormat("HH:mm");
                    try {
                        Date time1 = format.parse(o1[0]);
                        Date time2 = format.parse(o2[0]);
                        return time1.compareTo(time2);
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            // Convertir les données triées en un tableau bidimensionnel
            String[][] data = new String[dataList.size()][2];
            int i = 0;
            for (String[] rowData : dataList) {
                data[i++] = rowData;
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

 */
}
