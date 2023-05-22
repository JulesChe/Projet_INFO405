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

    public WeeklyAgendaController(WeeklyAgendaModel model) throws ParseException {

        this.model = model;

    }

    public WeeklyAgendaController() throws ParseException {

    }

    public void addPreviousWeekButtonActionListener(CreateGymnaseFrame createGymnaseFrame, Gymnase gymnaseSelectionne) {
        createGymnaseFrame.getPreviousWeekButton().addActionListener(e -> {
            model.goToPreviousWeek();
            updateWeekLabel(createGymnaseFrame);
            try {
                updateTasksPanel(createGymnaseFrame, gymnaseSelectionne);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }

        });

    }

    public void addNextWeekButtonActionListener(CreateGymnaseFrame createGymnaseFrame, Gymnase gymnaseSelectionne) {
        createGymnaseFrame.getNextWeekButton().addActionListener(e -> {
            model.goToNextWeek();
            updateWeekLabel(createGymnaseFrame);
            try {
                updateTasksPanel(createGymnaseFrame,gymnaseSelectionne);
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



    public void updateTasksPanel(CreateGymnaseFrame createGymnaseFrame, Gymnase gymnaseSelectionne) throws ParseException {



        Planning p = new Planning(); // Crée un planning (liste de créneaux)
        Creneau c1 = new Creneau(p.getDateDuJour(),p.getDateDuJour()); // Crée un créneau à ajouter
        String lundiWeek = c1.formatDate(model.getFormattedStartDate()); // Récupère le lundi de la semaine actuel
        p.getSemaineGymnase(lundiWeek, gymnaseSelectionne.getNom());
        ArrayList<Creneau> listeCreneaux = p.getListeCreneaux();

        // Parcourir chaque créneau de la liste
        for (Creneau creneau : listeCreneaux) {
            System.out.println(creneau);
            // Assurez-vous que la date est dans le bon format avant de l'envoyer à getDayOfWeek
            String correctedDateDebut = convertDateFormat(creneau.getDateDebut());

            String jourActuel = creneau.getDayOfWeek(correctedDateDebut);

            int jourFini = creneau.nbJour(jourActuel); // Numéro du jour du créneau ( ex : lundi = 1)
            String debutCren = convertDateFormat(creneau.getDateDebut()); // début du créneau
            String finCren = convertDateFormat(creneau.getDateFin()); // fin du créneau
            String asso = creneau.getAsso(); // asso du créneau

            // Convertir les heures de début et de fin en index de créneau horaire
            int debutIndex = convertHourToTimeSlotIndex(debutCren);  // Vous devez implémenter cette méthode
            int finIndex = convertHourToTimeSlotIndex(finCren);  // Vous devez implémenter cette méthode

            model.insertTimeSlot(jourFini, debutCren, finCren,asso); // Insert le créneau dans la méthode qui va gérer l'affichage
        }

        // Rafraîchir le panel pour voir les changements
        createGymnaseFrame.refreshPanel6();
    }

    private boolean isTimeSlotFilled(String dayOfWeek, String timeSlot) {
        // Vérifiez ici si le créneau horaire pour le jour de la semaine "dayOfWeek" et le créneau horaire "timeSlot" est rempli
        // Utilisez vos données existantes ou ajoutez une logique personnalisée pour déterminer si le créneau est pris
        // Par exemple, vous pouvez parcourir votre liste de créneaux et vérifier si le créneau correspondant existe pour ce jour de la semaine et ce créneau horaire

        // Exemple de logique pour vérifier si le créneau est rempli
        Planning p = new Planning(); // Remplacez par votre objet de planification existant
        for (Creneau creneau : p.getListeCreneaux()) { // Remplacez par votre liste de créneaux existante
            // Assurez-vous que la date est dans le bon format avant de l'envoyer à getDayOfWeek
            String correctedDateDebut = convertDateFormat(creneau.getDateDebut());
            String jourActuel = creneau.getDayOfWeek(correctedDateDebut);

            // Vérifiez si le créneau correspond au jour de la semaine et au créneau horaire
            if (jourActuel.equals(dayOfWeek) && creneau.getDateDebut().equals(timeSlot)) {
                return true; // Le créneau est rempli
            }
        }

        return false; // Le créneau n'est pas rempli
    }



    public int convertHourToTimeSlotIndex(String hourString) {

        int hour = Integer.parseInt(getHour(hourString));
        int minutes = Integer.parseInt(getMinute(hourString));

        int index = hour * 4 + minutes / 15;

        index -= 8 * 4;

        if (index < 0 || index >= TIME_SLOTS.length) {
            throw new IllegalArgumentException("The time provided is outside the range of time slots.");
        }

        return index;
    }

    public static String getHour(String dateString) {
        String[] parts = dateString.split(" ")[1].split(":");
        String hours = parts[0];
        return hours;
    }

    public static String getMinute(String dateString) {
        String[] parts = dateString.split(" ")[1].split(":");
        String minutes = parts[1];
        return minutes;
    }
    public String convertDateFormat(String inputDate) {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        LocalDateTime date = LocalDateTime.parse(inputDate, inputFormat);

        return outputFormat.format(date);
    }





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

