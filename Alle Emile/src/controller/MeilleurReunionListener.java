package controller;

import model.Creneau;
import model.Logistique;
import model.WeeklyAgendaModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.*;
import java.util.List;

public class MeilleurReunionListener implements ActionListener {

    private JPanel reunionPanel;
    private JTextField dateReu;

    private JPanel checkboxesPanel;

    private HashMap<Integer, ArrayList<String>> data;

    private HashMap<JCheckBox, Integer> checkboxKeys;

    private JPanel jourPanel;


    public MeilleurReunionListener(JPanel checkboxesPanel, JTextField dateReu, JPanel reunionPanel, HashMap<Integer, ArrayList<String>> data, HashMap<JCheckBox, Integer> checkboxKeys, JPanel jourPanel) {

        this.dateReu = dateReu;

        this.reunionPanel = reunionPanel;

        this.checkboxesPanel = checkboxesPanel;

        this.data = data;

        this.checkboxKeys = checkboxKeys;

        this.jourPanel = jourPanel;
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

            jourPanel.removeAll();

            for(int i = 0 ; i < 5; i++){

                WeeklyAgendaModel model = new WeeklyAgendaModel(null,null);

                String chaineJour = model.getWeekDay(i);

                HashMap<String, Integer> listeCreneau = patrick.getMeilleurDispo(jour,liste);

                Map.Entry<String, Integer> firstEntry = listeCreneau.entrySet().iterator().next();
                String creneau = firstEntry.getKey();
                Integer nbPersonnes = firstEntry.getValue();

                JLabel jourLabel = new JLabel("Le " +chaineJour + " Il y aura " + nbPersonnes + " personnes à " + creneau );
                JButton ajouterReu = new JButton("Ajouter Réunion");
                jourPanel.add(jourLabel);
                jourPanel.add(ajouterReu);



                Creneau c = new Creneau();
                jour = c.ajouterUnJour(jour);

            }
            jourPanel.repaint();
            jourPanel.revalidate();



        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }

    }


}



