package controller;

import model.WeeklyAgendaModel;
import vue.WeeklyAgendaView;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeeklyAgendaController {
    private WeeklyAgendaModel model;
    private WeeklyAgendaView view;

    public WeeklyAgendaController(WeeklyAgendaModel model, WeeklyAgendaView view) {
        this.model = model;
        this.view = view;

        // Ajoutez des listeners aux éléments d'interface utilisateur
        view.addPreviousWeekButtonActionListener(new PreviousWeekButtonListener());
        view.addNextWeekButtonActionListener(new NextWeekButtonListener());

        // Initialise l'affichage
        updateWeekLabel();
        updateTasksPanel();
    }

    private class PreviousWeekButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.goToPreviousWeek();
            updateWeekLabel();
            updateTasksPanel();
        }
    }

    private class NextWeekButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            model.goToNextWeek();
            updateWeekLabel();
            updateTasksPanel();
        }
    }

    private void updateWeekLabel() {
        String weekText = String.format("Semaine du %s au %s",
                model.getFormattedStartDate(),
                model.getFormattedEndDate());
        view.setWeekLabel(weekText);
    }

    private void updateTasksPanel() {
        view.getTasksPanel().removeAll();



        JPanel mainPanel = new JPanel(new BorderLayout());


        view.getTasksPanel().add(mainPanel);



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
            dialog.setLayout(new GridLayout(4, 2));

            dialog.add(new JLabel("Jour:"));
            JComboBox<String> dayComboBox = new JComboBox<>(model.getWeekDays());
            dialog.add(dayComboBox);

            dialog.add(new JLabel("Heure de début (format 24h, ex: 19:00):"));
            JTextField startTimeField = new JTextField();
            dialog.add(startTimeField);

            dialog.add(new JLabel("Heure de fin (format 24h, ex: 20:00):"));
            JTextField endTimeField = new JTextField();
            dialog.add(endTimeField);

            JButton addButton = new JButton("Ajouter");
            addButton.addActionListener(addEvent -> {
                int selectedDay = dayComboBox.getSelectedIndex();
                String startTime = startTimeField.getText();
                String endTime = endTimeField.getText();
                if (!startTime.isEmpty() && !endTime.isEmpty()) {
                    model.insertTimeSlot(selectedDay, startTime, endTime);
                    updateTasksPanel();
                    dialog.dispose();
                }
            });
            dialog.add(new JLabel());
            dialog.add(addButton);

            dialog.pack();
            dialog.setLocationRelativeTo(view.getFrame());
            dialog.setVisible(true);
        });
    }






}



