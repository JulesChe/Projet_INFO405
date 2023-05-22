package controller;

import model.*;
import vue.WeeklyAgendaView;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

public class WeeklyAgendaController {
    private WeeklyAgendaModel model;
    private WeeklyAgendaView view;



    public WeeklyAgendaController(WeeklyAgendaView view, WeeklyAgendaModel model) throws ParseException {
        this.view = view;
        this.model = model;

        updateWeekLabel();
        updateTasksPanel();

        addPreviousWeekButtonActionListener();
        addNextWeekButtonActionListener();
    }

    private void addPreviousWeekButtonActionListener() {
        view.getPreviousWeekButton().addActionListener(e -> {
            model.goToPreviousWeek();
            updateWeekLabel();
            try {
                updateTasksPanel();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }

        });

    }

    private void addNextWeekButtonActionListener() {
        view.getNextWeekButton().addActionListener(e -> {
            model.goToNextWeek();
            updateWeekLabel();
            try {
                updateTasksPanel();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
    public WeeklyAgendaModel getModel() {

        return model;
    }

    public WeeklyAgendaView getView() {
        return view;
    }


    private class PreviousWeekButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.goToPreviousWeek();
            updateWeekLabel();

            try {
                updateTasksPanel();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private class NextWeekButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.goToNextWeek();
            updateWeekLabel();

            try {
                updateTasksPanel();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void updateWeekLabel() {
        String weekText = String.format("Semaine du %s au %s",
                model.getFormattedStartDate(),
                model.getFormattedEndDate());
        view.setWeekLabel(weekText);
    }


    private void updateViewTimeSlots() {
        view.getTasksPanel().removeAll();

        for (int dayIndex = 0; dayIndex < model.getWeekDays().length; dayIndex++) {
            JList<String> timeSlotList = new JList<>(model.getDaysTimeSlots().get(dayIndex));
            JScrollPane scrollPane = new JScrollPane(timeSlotList);
            scrollPane.setBorder(new TitledBorder(model.getWeekDay(dayIndex)));
            view.getTasksPanel().add(scrollPane);
        }

        view.getTasksPanel().revalidate();
        view.getTasksPanel().repaint();
    }
    private void updateTasksPanel() throws ParseException {
        model.clearDaysTimeSlots();

        view.getTasksPanel().removeAll();


        JPanel mainPanel = new JPanel(new BorderLayout());


        view.getTasksPanel().add(mainPanel);

        // Création de la grille avec heures etc
        for (int i = 0; i < 7; i++) {
            JPanel dayPanel = new JPanel(new BorderLayout());
            String dayTitle = model.getWeekDay(i) + " " + model.getFormattedDateAt(i);
            dayPanel.setBorder(new TitledBorder(dayTitle));
            view.getTasksPanel().add(dayPanel);

            JPanel hourPanel = new JPanel(new GridLayout(19, 1));
            for (int hour = 0; hour < 19; hour++) {
                JPanel hourCell = new JPanel(new BorderLayout());
                String hourtitle = hour + " : 00";
                hourCell.setBorder(new TitledBorder(hourtitle));
                hourCell.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                hourPanel.add(hourCell);
            }

            dayPanel.add(new JScrollPane(hourPanel), BorderLayout.CENTER);
            dayPanel.setBorder(new TitledBorder(dayTitle));
            view.getTasksPanel().add(dayPanel);

            DefaultListModel<String> taskListModel = model.getDaysTimeSlots().get(i);
            JList<String> timeSlotList = new JList<>(taskListModel);
            timeSlotList.setCellRenderer(new TimeSlotCellRenderer());
            JScrollPane scrollPane = new JScrollPane(timeSlotList);
            dayPanel.add(scrollPane, BorderLayout.CENTER);

        }

        view.getTasksPanel().revalidate();
        view.getTasksPanel().repaint();


        //Récupère les créneaux de la semaine
        Planning p = new Planning();
        Creneau c1 = new Creneau(p.getDateDuJour(),p.getDateDuJour());
        String lundiWeek = c1.formatDate(model.getFormattedStartDate());
        p.getSemaine(lundiWeek);
        Logistique patrick = new Logistique();
        ArrayList<Creneau> listeCreneaux = p.listeCreneaux;
        if (listeCreneaux!=null){

            listeCreneaux = c1.transformCreneau(listeCreneaux);
        }

        listeCreneaux = patrick.sortCreneaux(listeCreneaux);
        //Ajoutes tous les créneaux de la semaine
        WeeklyAgendaModel modele = new WeeklyAgendaModel(null, null);


        for (Creneau creneau : listeCreneaux) {
            String jourActuel = creneau.getDayOfWeek();
            int jourFini = creneau.nbJour(jourActuel);
            String debutCren = creneau.getDateDebut();
            String finCren = creneau.getDateFin();
            String asso = creneau.getAsso();
            model.insertTimeSlot(jourFini, debutCren, finCren,asso);
        }

        view.getTasksPanel().revalidate();
        view.getTasksPanel().repaint();

        // Créer un panneau pour le bouton "Ajouter créneau"
        JPanel addTimeSlotPanel = new JPanel();
        view.getFrame().getContentPane().add(addTimeSlotPanel, BorderLayout.SOUTH);

        // Ajouter un bouton "Ajouter créneau"
        JButton addTimeSlotButton = new JButton("Ajouter créneau");
        addTimeSlotPanel.add(addTimeSlotButton);
        addTimeSlotButton.addActionListener(e -> {
            // Afficher un dialogue personnalisé avec des champs pour sélectionner le jour, l'heure de début et l'heure de fin
            JDialog dialog = new JDialog(view.getFrame(), "Ajouter créneau", true);
            dialog.setLayout(new GridLayout(5, 2));

            dialog.setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.anchor = GridBagConstraints.WEST;
            constraints.insets = new Insets(5, 5, 5, 5);

// Label et champ de texte pour la date
            JLabel dateLabel = new JLabel("Jour (format : yyyy/mm/dd) :");
            JTextField dateField = new JTextField(15);
            constraints.gridx = 0;
            constraints.gridy = 0;
            dialog.add(dateLabel, constraints);
            constraints.gridx = 1;
            dialog.add(dateField, constraints);

// Label et champ de texte pour l'heure de début
            JLabel startTimeLabel = new JLabel("Heure de début (format 24h, ex: 19:00):");
            JTextField startTimeField = new JTextField(10);
            constraints.gridx = 0;
            constraints.gridy = 1;
            dialog.add(startTimeLabel, constraints);
            constraints.gridx = 1;
            dialog.add(startTimeField, constraints);

// Label et champ de texte pour l'heure de fin
            JLabel endTimeLabel = new JLabel("Heure de fin (format 24h, ex: 20:00):");
            JTextField endTimeField = new JTextField(10);
            constraints.gridx = 0;
            constraints.gridy = 2;
            dialog.add(endTimeLabel, constraints);
            constraints.gridx = 1;
            dialog.add(endTimeField, constraints);

// Label et champ de texte pour le nom de l'association
            JLabel associationLabel = new JLabel("Nom de l'asso :");
            JTextField associationField = new JTextField(15);
            constraints.gridx = 0;
            constraints.gridy = 3;
            dialog.add(associationLabel, constraints);
            constraints.gridx = 1;
            dialog.add(associationField, constraints);

// Label et champ de texte pour le gymnase
            JLabel gymnaseLabel = new JLabel("Gymnase :");
            JTextField gymnaseField = new JTextField(15);
            constraints.gridx = 0;
            constraints.gridy = 4;
            dialog.add(gymnaseLabel, constraints);
            constraints.gridx = 1;
            dialog.add(gymnaseField, constraints);



            JButton addButton = new JButton("Ajouter");
            addButton.addActionListener(addEvent -> {
                String dateTime = dateField.getText();
                String startTime = startTimeField.getText();
                String endTime = endTimeField.getText();
                String gymnase = gymnaseField.getText();

                String dateDebut = dateTime+" "+startTime;
                String dateFin = dateTime+" "+endTime;
                Creneau c = new Creneau(dateDebut,dateFin);
                String jour = c.getDayOfWeek();
                int selectedDay = c.nbJour(jour);
                System.out.println(selectedDay);

                String taskDescription = associationField.getText();
                if (!startTime.isEmpty() && !endTime.isEmpty() && !taskDescription.isEmpty()) {
                    model.insertTimeSlot(selectedDay, startTime, endTime,taskDescription);
                    try {
                        updateTasksPanel();
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                    dialog.dispose();
                }
                int idAsso = patrick.getIDAsso(taskDescription);

                patrick.ajoutCreneau(dateDebut,dateFin,idAsso,gymnase);

            });
            dialog.add(new JLabel());
            dialog.add(addButton);

            dialog.pack();
            dialog.setLocationRelativeTo(view.getFrame());
            dialog.setVisible(true);
        });
    }


}