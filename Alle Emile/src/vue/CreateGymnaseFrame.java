package vue;

import model.WeeklyAgendaModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

public class CreateGymnaseFrame extends JInternalFrame {


    private JLabel weekLabel;
    private WeeklyAgendaModel model;
    public CreateGymnaseFrame(WeeklyAgendaModel model) throws PropertyVetoException, SQLException {
        this.model = model;
        initComponents();
    }

    private void initComponents() throws PropertyVetoException, SQLException {
        //======== Calendrier1 ========


        Calendrier1 = new JInternalFrame();
        jpC1 = new JPanel();
        tPC1 = new JTabbedPane();
        AGC1 = new JPanel();
        NavAGC1 = new JPanel();
        button_semaine_prec = new JButton();
        button_semaine_suiv = new JButton();
        weekLabel = new JLabel();
        Agenda = new JPanel();
        PAjouter_creneau = new JPanel();
        button_ajouter_creneau = new JButton();
        DCG1 = new JPanel();
        scrollPane2 = new JScrollPane();
        table1 = new JTable();
        INC1 = new JPanel();
        scrollPane3 = new JScrollPane();
        table2 = new JTable();
        panel1 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        scrollPane4 = new JScrollPane();
        list1 = new JList<>();
        panel5 = new JPanel();
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        button1 = new JButton();
        button2 = new JButton();
        {

            var Calendrier1ContentPane = Calendrier1.getContentPane();
            Calendrier1ContentPane.setLayout(new BorderLayout());

            //======== jpC1 ========
            {
                jpC1.setLayout(new BorderLayout());

                //======== tPC1 ========
                {

                    //======== AGC1 ========
                    {
                        AGC1.setBorder(new TitledBorder("Emploi du temps"));
                        AGC1.setLayout(new BorderLayout());

                        //======== NavAGC1 ========
                        {
                            NavAGC1.setLayout(new BorderLayout());

                            //---- button_semaine_prec ----
                            button_semaine_prec.setText("Semane pr\u00e9c\u00e9dente");
                            NavAGC1.add(button_semaine_prec, BorderLayout.WEST);

                            //---- button_semaine_suiv ----
                            button_semaine_suiv.setText("Semaine suivante");
                            NavAGC1.add(button_semaine_suiv, BorderLayout.EAST);

                            //---- weekLabel ----
                            weekLabel.setText("text");
                            NavAGC1.add(weekLabel, BorderLayout.CENTER);
                        }
                        AGC1.add(NavAGC1, BorderLayout.NORTH);

                        //======== Agenda ========
                        {
                            Agenda.setLayout(new GridLayout(1, 7));
                        }
                        AGC1.add(Agenda, BorderLayout.CENTER);

                        //======== PAjouter_creneau ========
                        {
                            PAjouter_creneau.setLayout(new FlowLayout());

                            //---- button_ajouter_creneau ----
                            button_ajouter_creneau.setText("Ajouter cr\u00e9neau");
                            PAjouter_creneau.add(button_ajouter_creneau);
                        }
                        AGC1.add(PAjouter_creneau, BorderLayout.SOUTH);
                    }
                    tPC1.addTab("Agenda", AGC1);

                    //======== DCG1 ========
                    {
                        DCG1.setLayout(new BorderLayout());

                        //======== scrollPane2 ========
                        {

                            //---- table1 ----
                            table1.setCellSelectionEnabled(true);
                            table1.setModel(new DefaultTableModel(
                                    new Object[][] {
                                            {null, null, null},
                                            {null, null, null},
                                            {null, null, null},
                                    },
                                    new String[] {
                                            "Creneau", "Association", "Action"
                                    }
                            ));
                            scrollPane2.setViewportView(table1);
                        }
                        DCG1.add(scrollPane2, BorderLayout.CENTER);
                    }
                    tPC1.addTab("Demande Cr\u00e9neau", DCG1);

                    //======== INC1 ========
                    {
                        INC1.setLayout(new BorderLayout());

                        //======== scrollPane3 ========
                        {

                            //---- table2 ----
                            table2.setModel(new DefaultTableModel(
                                    new Object[][] {
                                            {null, null},
                                            {null, null},
                                    },
                                    new String[] {
                                            "Incident", "Date"
                                    }
                            ));
                            scrollPane3.setViewportView(table2);
                        }
                        INC1.add(scrollPane3, BorderLayout.CENTER);
                    }
                    tPC1.addTab("Incident", INC1);

                    //======== panel1 ========
                    {
                        panel1.setLayout(new BorderLayout());

                        //======== panel3 ========
                        {
                            panel3.setLayout(new BorderLayout());

                            //======== panel4 ========
                            {
                                panel4.setLayout(new BorderLayout());

                                //======== scrollPane4 ========
                                {

                                    //---- list1 ----
                                    list1.setModel(new AbstractListModel<String>() {
                                        String[] values = {
                                                "Gardien1",
                                                "Gardien2"
                                        };
                                        @Override
                                        public int getSize() { return values.length; }
                                        @Override
                                        public String getElementAt(int i) { return values[i]; }
                                    });
                                    scrollPane4.setViewportView(list1);
                                }
                                panel4.add(scrollPane4, BorderLayout.CENTER);

                                //======== panel5 ========
                                {
                                    panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));

                                    //---- label1 ----
                                    label1.setText("Nom :");
                                    panel5.add(label1);
                                    panel5.add(textField1);

                                    //---- label2 ----
                                    label2.setText("Prenom :");
                                    panel5.add(label2);
                                    panel5.add(textField2);

                                    //---- button1 ----
                                    button1.setText("Ajouter");
                                    panel5.add(button1);

                                    //---- button2 ----
                                    button2.setText("Supprimer");
                                    panel5.add(button2);
                                }
                                panel4.add(panel5, BorderLayout.SOUTH);
                            }
                            panel3.add(panel4, BorderLayout.CENTER);
                        }
                        panel1.add(panel3, BorderLayout.CENTER);
                    }
                    tPC1.addTab("Habilitation", panel1);
                }
                jpC1.add(tPC1, BorderLayout.CENTER);
            }
            Calendrier1ContentPane.add(jpC1, BorderLayout.CENTER);
        }
    }

    public JInternalFrame getCalendrier1() {
        return Calendrier1;
    }

    private JInternalFrame Calendrier1;
    private JPanel jpC1;
    private JTabbedPane tPC1;
    private JPanel AGC1;
    private JPanel NavAGC1;
    private JButton button_semaine_prec;
    private JButton button_semaine_suiv;
    private JPanel Agenda;
    private JPanel PAjouter_creneau;
    private JButton button_ajouter_creneau;
    private JPanel DCG1;
    private JScrollPane scrollPane2;
    private JTable table1;
    private JPanel INC1;
    private JScrollPane scrollPane3;
    private JTable table2;
    private JPanel panel1;
    private JPanel panel3;
    private JPanel panel4;
    private JScrollPane scrollPane4;
    private JList<String> list1;
    private JPanel panel5;
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JButton button1;
    private JButton button2;


}


