package vue;

import controller.AjouterAssociationListener;
import crud.CrudCompteAssoDAO;
import model.Association;
import model.Creneau;
import model.Planning;
import model.WeeklyAgendaModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class WeeklyAgendaView {
    public JFrame frame;
    private JLabel weekLabel;
    private JPanel tasksPanel;

    private JTabbedPane tabbedPane;

    // Ajoutez ces deux lignes pour déclarer les variables d'instance
    private JButton previousWeekButton;
    private JButton nextWeekButton;

    private WeeklyAgendaModel modele;

    public WeeklyAgendaView() throws SQLException {
        initComponents();
    }


    private void creerAgenda(JTabbedPane tabbedPane) {
        // Ajout de l'onglet "Emploi du temps"
        JPanel agendaPanel = new JPanel(new BorderLayout());
        agendaPanel.setBorder(new TitledBorder("Emploi du temps"));
        tasksPanel = new JPanel(new GridLayout(1, 7));
        agendaPanel.add(tasksPanel, BorderLayout.CENTER);

        // Initialisez weekLabel et définissez sa taille et son alignement
        weekLabel = new JLabel();
        weekLabel.setHorizontalAlignment(SwingConstants.CENTER);
        weekLabel.setFont(new Font("Tahoma", Font.BOLD, 18));

        // Ajoutez weekLabel au panel agendaPanel
        agendaPanel.add(weekLabel, BorderLayout.NORTH);

        // Création des boutons
        previousWeekButton = new JButton("Semaine précédente"); // Modifiez cette ligne
        nextWeekButton = new JButton("Semaine suivante"); // Modifiez cette ligne

        // Ajout des boutons et du label au panel
        JPanel navigationPanel = new JPanel();
        navigationPanel.setLayout(new BoxLayout(navigationPanel, BoxLayout.LINE_AXIS));
        navigationPanel.add(previousWeekButton);

        // Initialisation de weekLabel et ajout au panel
        weekLabel = new JLabel();
        navigationPanel.add(Box.createHorizontalGlue());
        navigationPanel.add(weekLabel);
        navigationPanel.add(Box.createHorizontalGlue());

        navigationPanel.add(nextWeekButton);

        agendaPanel.add(navigationPanel, BorderLayout.NORTH);

        tabbedPane.addTab("Emploi du temps", null, agendaPanel, "Emploi du temps");


    }




    private void initComponents() throws SQLException {
        frame = new JFrame("Agenda hebdomadaire");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
        // Ajout de l'onglet "Demande de créneau"
        Planning p = new Planning();
        p.getAllCreneaux("demande");
        // Créez un JPanel pour contenir le tableau et les autres éléments de l'onglet
        JPanel requestPanelB = new JPanel(new BorderLayout());

        // Créez un tableau JTable
        int taille = p.getTaille();
        int rowCount = taille; // nombre de lignes du tableau
        int columnCount = 1; // nombre de colonnes du tableau
        Object[][] data = new Object[rowCount][columnCount];

        // remplir le tableau avec les données
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                Creneau c = p.getListeCreneaux().get(i);
                data[i][j] = "Début : " + c.getDateDebut() + "   Fin : " + c.getDateDebut() + " Asso : " + c.getAsso();
            }
        }
        String[] columnNames = {"Demandes des associations"};
        JTable table = new JTable(data, columnNames);

        // Ajoutez le JTable à un JScrollPane pour permettre le défilement si nécessaire
        JScrollPane scrollPane = new JScrollPane(table);

        //Boutton pour accepter et refuser des demandes
        JButton boutonAdd = new JButton("Accepter");
        JButton boutonDel = new JButton("Refuser");

        // Ajoutez le JScrollPane au JPanel créé à l'étape 1
        requestPanelB.add(scrollPane, BorderLayout.CENTER);
        JPanel acceptButton = new JPanel(new BorderLayout());
        requestPanelB.add(acceptButton,BorderLayout.SOUTH);
        acceptButton.add(boutonAdd,BorderLayout.NORTH);
        acceptButton.add(boutonDel,BorderLayout.SOUTH);


        tabbedPane = new JTabbedPane();

        creerAgenda(tabbedPane);


        // Ajoutez le JPanel à l'onglet "Demande de créneau" du JTabbedPane
        tabbedPane.addTab("Demande de créneau", null, requestPanelB, "Demande de créneau");


        vueAssociation();



        vueGymnase();




        // Ajout de l'onglet "Incidents"
        JPanel incidentPanel = new JPanel();
        incidentPanel.setBorder(new TitledBorder("Incidents"));
        tabbedPane.addTab("Incidents", null, incidentPanel, "Incidents");

        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }


    private void demanderCreneau(){

    }

    private void vueGymnase(){
        JPanel gymnasePanel = new JPanel();

// Création d'un modèle de liste pour stocker les gymnases
        DefaultListModel<String> listModel2 = new DefaultListModel<String>();

// Création d'une liste à partir du modèle
        JList<String> gymnaseList = new JList<String>(listModel2);

// Ajout des gymnases à la liste (remplacez ce code par votre propre logique de récupération des gymnases)
        listModel2.addElement("Gymnase 1 - Paris");
        listModel2.addElement("Gymnase 2 - Marseille");
        listModel2.addElement("Gymnase 3 - Lyon");

// Ajout de la liste au panneau avec un titre
        gymnasePanel.add(new JLabel("Liste des gymnases :"));
        gymnasePanel.add(new JScrollPane(gymnaseList));

// Création des champs de texte et du bouton pour ajouter un nouveau gymnase
        JTextField nameField2 = new JTextField(20);
        JTextField locationField = new JTextField(20);
        JButton addButton = new JButton("Ajouter un gymnase");

// Ajout d'un gestionnaire d'événements pour le bouton
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code pour ajouter un nouveau gymnase avec les valeurs des champs de texte
                String name = nameField2.getText();
                String location = locationField.getText();

                // Ajout du nouveau gymnase à la liste
                listModel2.addElement(name + " - " + location);

                // Code pour ajouter le gymnase (à remplacer par votre propre logique)
                System.out.println("Nouveau gymnase ajouté : " + name + " - " + location);

                // Réinitialisation des champs de texte
                nameField2.setText("");
                locationField.setText("");
            }
        });

// Création des champs de texte et du bouton pour supprimer un gymnase
        JTextField deleteField = new JTextField(20);
        JButton deleteButton = new JButton("Supprimer un gymnase");

// Ajout d'un gestionnaire d'événements pour le bouton
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code pour supprimer le gymnase avec le nom spécifié
                String name = deleteField.getText();
                int index = listModel2.indexOf(name);
                if (index >= 0) {
                    listModel2.removeElementAt(index);

                    // Code pour supprimer le gymnase (à remplacer par votre propre logique)
                    System.out.println("Gymnase supprimé : " + name);
                } else {
                    // Le gymnase n'existe pas dans la liste
                    System.out.println("Le gymnase " + name + " n'existe pas.");
                }

                // Réinitialisation du champ de texte
                deleteField.setText("");
            }
        });

// Ajout des champs de texte et des boutons au panneau
        gymnasePanel.add(new JLabel("Nom du nouveau gymnase :"));
        gymnasePanel.add(nameField2);
        gymnasePanel.add(new JLabel("Lieu :"));
        gymnasePanel.add(locationField);
        gymnasePanel.add(addButton);
        gymnasePanel.add(new JLabel("Nom du gymnase à supprimer :"));
        gymnasePanel.add(deleteField);
        gymnasePanel.add(deleteButton);

// Ajout du panneau à l'onglet
        tabbedPane.addTab("Gymnase", null, gymnasePanel, "Gymnase");

    }

    private void vueAssociation() throws SQLException {

        Connection connection = null;

        JPanel associationPanel = new JPanel();

        // Création d'un modèle de liste pour stocker les noms des associations
        DefaultListModel<String> listModel = new DefaultListModel<String>();

        CrudCompteAssoDAO assoDAO = new CrudCompteAssoDAO(connection);


        Association a = new Association("recupNom");

        ArrayList<Association> listAsso = a.getAllAsso();

        for (int i = 0; i < listAsso.size(); i++) {
            listModel.addElement(listAsso.get(i).getNom());
        }


        // Création d'une liste à partir du modèle
        JList<String> associationList = new JList<String>(listModel);

        // Ajout de la liste au panneau avec un titre
        associationPanel.add(new JLabel("Associations existantes :"));
        associationPanel.add(new JScrollPane(associationList));

        // Création des champs de texte et du bouton pour ajouter une nouvelle association
        JTextField nameField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JButton submitButton = new JButton("Créer l'association");

        // Ajout d'un gestionnaire d'événements pour le bouton
        submitButton.addActionListener(new AjouterAssociationListener(nameField,passwordField,listModel));

        // Ajout des champs de texte et du bouton au panneau
        associationPanel.add(new JLabel("Nom de la nouvelle association :"));
        associationPanel.add(nameField);
        associationPanel.add(new JLabel("Mot de passe :"));
        associationPanel.add(passwordField);
        associationPanel.add(submitButton);

        // Ajout du panneau à l'onglet
        tabbedPane.addTab("Association", null, associationPanel, "Association");






    }






    public void setWeekLabel(String weekText) {
        weekLabel.setText(weekText);
    }

    public void setTasksPanel(JPanel newTasksPanel) {
        tasksPanel.removeAll();
        tasksPanel.add(newTasksPanel);
        tasksPanel.revalidate();
        tasksPanel.repaint();
    }

    public void showView() {
        frame.setVisible(true);
    }

    public JButton getPreviousWeekButton() {
        return previousWeekButton;
    }

    public JButton getNextWeekButton() {
        return nextWeekButton;
    }



    public JPanel getTasksPanel() {
        return tasksPanel;
    }
    public JFrame getFrame() {
        return frame;
    }


}