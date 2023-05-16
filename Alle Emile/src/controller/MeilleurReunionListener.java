package controller;

import model.Logistique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class MeilleurReunionListener implements ActionListener {

    private JPanel reunionPanel;
    private JTextField dateReu;

    private JPanel checkboxesPanel;

    private HashMap<Integer, ArrayList<String>> data;

    private HashMap<JCheckBox, Integer> checkboxKeys;

    public MeilleurReunionListener(JPanel checkboxesPanel, JTextField dateReu, JPanel reunionPanel, HashMap<Integer, ArrayList<String>> data,HashMap<JCheckBox, Integer> checkboxKeys) {

        this.dateReu = dateReu;

        this.reunionPanel = reunionPanel;

        this.checkboxesPanel = checkboxesPanel;

        this.data = data;

        this.checkboxKeys = checkboxKeys;

    }

    private List<Integer> getSelectedCheckboxes(JPanel panel) {
        List<Integer> selectedKeys = new ArrayList<>();
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JCheckBox) {
                JCheckBox checkbox = (JCheckBox) comp;
                if (checkbox.isSelected()) {
                    // Récupération de la clé correspondante à partir de la Map
                    Integer key = checkboxKeys.get(checkbox);
                    selectedKeys.add(key);
                }
            }
        }
        return selectedKeys;
    }

    public ArrayList<Integer> recupereIdListePrio(HashMap<Integer, ArrayList<String>> data, JPanel checkboxesPanel){

        List<Integer> selected = getSelectedCheckboxes(checkboxesPanel);

        ArrayList<Integer> res = new ArrayList<>();

        for(Map.Entry mapentry : data.entrySet()){


            for(int id : selected){

                if(mapentry.getKey().equals(id)){

                    res.add(id);
                }
            }

        }

        System.out.print(res);

        return res;


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Logistique patrick = new Logistique();
        String jour = dateReu.getText();

        ArrayList<Integer> liste = new ArrayList<>();

        try {


            for (Component comp : checkboxesPanel.getComponents()) {
                if (comp instanceof JCheckBox) {
                    JCheckBox checkbox = (JCheckBox) comp;
                    if (checkbox.isSelected()) {
                        // Récupération de la clé correspondante à partir de la Map
                        if(checkbox.getText().equals("Gardien")){

                            liste = patrick.getAllIdGardien();
                        } else if (checkbox.getText().equals("Logistique")) {

                            liste = patrick.getAllIdLogistique();
                        } else if (checkbox.getText().equals("Directeur")) {

                            liste = patrick.getIdPO();
                            
                        } else {

                            liste = this.recupereIdListePrio(data, checkboxesPanel);
                        }

                    }
                }
            }




            HashMap<String, Integer> hashmap = patrick.getMeilleurDispo(jour,liste);

            // Stocker les données dans un tableau ArrayList
            ArrayList<String[]> dataList = new ArrayList<>();
            for (HashMap.Entry<String, Integer> entry : hashmap.entrySet()) {
                dataList.add(new String[]{entry.getKey(), String.valueOf(entry.getValue())});
            }

            // Trier les données par ordre croissant en utilisant un comparateur personnalisé pour le format hh:mm
            Collections.sort(dataList, new Comparator<String[]>() {
                @Override
                public int compare(String[] o1, String[] o2) {
                    DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
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


}



