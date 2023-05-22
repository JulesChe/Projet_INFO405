package controller;

import model.*;
import vue.AgendaVueFinal;
import vue.CreateGymnaseFrame;

import java.text.ParseException;

public class WeeklyAgendaController {
    private WeeklyAgendaModel model;
    private CreateGymnaseFrame view2;
    private AgendaVueFinal view;



    public WeeklyAgendaController(CreateGymnaseFrame view2, WeeklyAgendaModel model,AgendaVueFinal view) throws ParseException {
        this.view2 = view2;
        this.model = model;
        this.view = view;

    }

    public void addPreviousWeekButtonActionListener(CreateGymnaseFrame createGymnaseFrame) {
        createGymnaseFrame.getPreviousWeekButton().addActionListener(e -> {
            model.goToPreviousWeek();
            updateWeekLabel(createGymnaseFrame);
            try {
                updateTasksPanel();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }

        });

    }

    public void addNextWeekButtonActionListener(CreateGymnaseFrame createGymnaseFrame) {
        createGymnaseFrame.getNextWeekButton().addActionListener(e -> {
            model.goToNextWeek();
            updateWeekLabel(createGymnaseFrame);
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

    public void updateWeekLabel(CreateGymnaseFrame createGymnaseFrame) {
        String weekText = String.format("Semaine du %s au %s",
                model.getFormattedStartDate(),
                model.getFormattedEndDate());
        createGymnaseFrame.setWeekLabel(weekText);
    }



    private void updateTasksPanel() throws ParseException {
        /*
        model.clearDaysTimeSlots();

        //Récupère les créneaux de la semaine
        Planning p = new Planning(); // Crée un planning (liste de créneaux)

        Creneau c1 = new Creneau(p.getDateDuJour(),p.getDateDuJour()); // Crée un créneau à ajouter
        String lundiWeek = c1.formatDate(model.getFormattedStartDate()); // Récupère le lundi de la semaine actuel

        p.getSemaine(lundiWeek); // Récupère tous les créneaux d'une semaine et les met dans un planning

        Logistique patrick = new Logistique();
        ArrayList<Creneau> listeCreneaux = p.listeCreneaux;
        if (listeCreneaux!=null){ // ajoute tous ces créneaux à une liste de créneau de la Logistique

            listeCreneaux = c1.transformCreneau(listeCreneaux);
        }

        listeCreneaux = patrick.sortCreneaux(listeCreneaux); // trie les créneaux en fonction de leur date


        //Ajoutes tous les créneaux de la semaine

        WeeklyAgendaModel modele = new WeeklyAgendaModel(null, null); // Réinitialise la vue des créneaux au changement de semaine

        for (Creneau creneau : listeCreneaux) { // Parcours les créneaux de la semaine
            String jourActuel = creneau.getDayOfWeek(); // jour du créneau
            int jourFini = creneau.nbJour(jourActuel); // Numéro du jour du créneau ( ex : lundi = 1)
            String debutCren = creneau.getDateDebut(); // début du créneau
            String finCren = creneau.getDateFin(); // fin du créneau
            String asso = creneau.getAsso(); // asso du créneau
            model.insertTimeSlot(jourFini, debutCren, finCren,asso); // Insert le créneau dans la méthode qui va gérer l'affichage
        }

        view.getTasksPanel().revalidate(); // Une fois tous les créneaux de la semaine ajouté
        view.getTasksPanel().repaint();    // on affiche tous ça.



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



            dialog.add(new JLabel("Jour (format : yyyy/mm/dd) :"));
            JTextField dateField = new JTextField();
            dialog.add(dateField);



            dialog.add(new JLabel("Heure de début (format 24h, ex: 19:00):"));
            JTextField startTimeField = new JTextField();
            dialog.add(startTimeField);

            dialog.add(new JLabel("Heure de fin (format 24h, ex: 20:00):"));
            JTextField endTimeField = new JTextField();
            dialog.add(endTimeField);

            dialog.add(new JLabel("Nom de l'asso :"));
            JTextField taskDescriptionField = new JTextField();
            dialog.add(taskDescriptionField);

            dialog.add(new JLabel("Gymnase :"));
            JTextField gymnaseField = new JTextField();
            dialog.add(gymnaseField);

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

                String taskDescription = taskDescriptionField.getText();
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
    }*/
    }

    }