package vue;

import model.Creneau;
import model.Planning;
import model.WeeklyAgendaModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class WeeklyAgendaView {
    public JFrame frame;
    private JLabel weekLabel;
    private JPanel tasksPanel;

    private JTabbedPane tabbedPane;

    // Ajoutez ces deux lignes pour déclarer les variables d'instance
    private JButton previousWeekButton;
    private JButton nextWeekButton;

    private WeeklyAgendaModel modele;

    public WeeklyAgendaView() {
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

        // Création d'un JTabbedPane

    }




    private void initComponents() {
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

        // Ajout de l'onglet "gymnasePanel"
        JPanel associationPanel = new JPanel();
        associationPanel.setBorder(new TitledBorder("Association"));
        tabbedPane.addTab("Association", null, associationPanel, "Association");

        // Ajout de l'onglet "gymnasePanel"
        JPanel gymnasePanel = new JPanel();
        gymnasePanel.setBorder(new TitledBorder("Gymnase"));
        tabbedPane.addTab("Gymnase", null, gymnasePanel, "Gymnase");

        // Ajout de l'onglet "Incidents"
        JPanel incidentPanel = new JPanel();
        incidentPanel.setBorder(new TitledBorder("Incidents"));
        tabbedPane.addTab("Incidents", null, incidentPanel, "Incidents");

        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
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