/*
 * Created by JFormDesigner on Mon May 08 14:01:05 CEST 2023
 */

package vue;

import javax.swing.table.*;
import model.WeeklyAgendaModel;

import java.awt.*;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import controller.WeeklyAgendaController;



import controller.AjouterAssociationListener;
import controller.DemandeCreneauListener;
import controller.WeeklyAgendaController;
import crud.CrudCompteAssoDAO;
import crud.CrudGymnaseDAO;
import model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * @author baptisteaudinet
 */


public class AgendaVue  {

    private JLabel weekLabel;
    private WeeklyAgendaModel modele;
    public AgendaVue(WeeklyAgendaModel modele) throws PropertyVetoException {
        this.modele = modele;
        initComponents();
    }

    private void initComponents() throws PropertyVetoException {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - baptiste audinet
        WIN = new JFrame();
        C1 = new JTabbedPane();
        AG = new JPanel();
        Nav = new JPanel();
        scrollPane1 = new JScrollPane();
        Arborescence = new JTree();
        Frame = new JDesktopPane();
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
        AS = new JPanel();
        GY = new JPanel();
        panel2 = new JPanel();
        panel8 = new JPanel();
        scrollPane6 = new JScrollPane();
        table3 = new JTable();
        scrollPane7 = new JScrollPane();
        list2 = new JList();
        Menu = new JPanel();
        menuBar1 = new JMenuBar();
        menu3 = new JMenu();
        menuItem1 = new JMenuItem();
        menu6 = new JMenu();
        menu7 = new JMenu();
        menu8 = new JMenu();
        menuItem2 = new JMenuItem();
        menuItem3 = new JMenuItem();
        menu5 = new JMenu();
        menuItem4 = new JMenuItem();
        menuItem5 = new JMenuItem();
        menu9 = new JMenu();
        menuItem6 = new JMenuItem();
        menuItem7 = new JMenuItem();
        menuItem8 = new JMenuItem();

        //======== WIN ========
        {
            WIN.setMinimumSize(new Dimension(1000, 600));
            WIN.setForeground(SystemColor.menu);
            WIN.setTitle("Agenda Collaboratif");
            var WINContentPane = WIN.getContentPane();
            WINContentPane.setLayout(new BorderLayout());

            //======== C1 ========
            {

                //======== AG ========
                {
                    AG.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
                    EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax. swing. border. TitledBorder. CENTER, javax. swing
                    . border. TitledBorder. BOTTOM, new java .awt .Font ("Dialo\u0067" ,java .awt .Font .BOLD ,12 ),
                    java. awt. Color. red) ,AG. getBorder( )) ); AG. addPropertyChangeListener (new java. beans. PropertyChangeListener( )
                    { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("borde\u0072" .equals (e .getPropertyName () ))
                    throw new RuntimeException( ); }} );
                    AG.setLayout(new BorderLayout());

                    //======== Nav ========
                    {
                        Nav.setLayout(new BorderLayout());

                        //======== scrollPane1 ========
                        {

                            //---- Arborescence ----
                            Arborescence.setMinimumSize(new Dimension(150, 0));
                            Arborescence.setModel(new DefaultTreeModel(
                                new DefaultMutableTreeNode("Gymnases") {
                                    {
                                        add(new DefaultMutableTreeNode("Gymnase1"));
                                        add(new DefaultMutableTreeNode("Gymnase2"));
                                    }
                                }));
                            Arborescence.setMaximumSize(new Dimension(200, 56));
                            Arborescence.setPreferredSize(new Dimension(150, 56));
                            scrollPane1.setViewportView(Arborescence);
                        }
                        Nav.add(scrollPane1, BorderLayout.CENTER);
                    }
                    AG.add(Nav, BorderLayout.WEST);

                    //======== Frame ========
                    {

                        //======== Calendrier1 ========
                        {
                            Calendrier1.setVisible(true);
                            Calendrier1.setClosable(true);
                            Calendrier1.setMaximum(true);
                            Calendrier1.setMaximizable(true);
                            Calendrier1.setResizable(true);
                            Calendrier1.setPreferredSize(new Dimension(500, 500));
                            Calendrier1.setTitle("Gymnase1");
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
                        Frame.add(Calendrier1, JLayeredPane.DEFAULT_LAYER);
                        Calendrier1.setBounds(0, 0, 800, 505);
                    }
                    AG.add(Frame, BorderLayout.CENTER);
                }
                C1.addTab("Calendrier", AG);

                //======== AS ========
                {
                    AS.setLayout(new BorderLayout());
                }
                C1.addTab("Association", AS);

                //======== GY ========
                {
                    GY.setLayout(new BorderLayout());
                }
                C1.addTab("Gymnase", GY);

                //======== panel2 ========
                {
                    panel2.setLayout(new BorderLayout());

                    //======== panel8 ========
                    {
                        panel8.setLayout(new FlowLayout());

                        //======== scrollPane6 ========
                        {

                            //---- table3 ----
                            table3.setModel(new DefaultTableModel(
                                new Object[][] {
                                    {"Emilien", "Gardien", ""},
                                    {"Justin", "Gardien", null},
                                    {"Bastien", "Gardien", null},
                                    {"Patrick", "Logistique", null},
                                    {"Jason", "Patron", null},
                                },
                                new String[] {
                                    "Employ\u00e9s", "R\u00f4le", "Selection"
                                }
                            ));
                            table3.setPreferredScrollableViewportSize(new Dimension(450, 150));
                            scrollPane6.setViewportView(table3);
                        }
                        panel8.add(scrollPane6);

                        //======== scrollPane7 ========
                        {
                            scrollPane7.setViewportView(list2);

                        }
                        panel8.add(scrollPane7);
                    }
                    panel2.add(panel8, BorderLayout.NORTH);
                }
                C1.addTab("Reunion", panel2);
            }
            WINContentPane.add(C1, BorderLayout.CENTER);

            //======== Menu ========
            {
                Menu.setOpaque(false);
                Menu.setLayout(new FlowLayout(FlowLayout.LEFT));

                //======== menuBar1 ========
                {

                    //======== menu3 ========
                    {
                        menu3.setText("Fichier");

                        //---- menuItem1 ----
                        menuItem1.setText("Element1");
                        menu3.add(menuItem1);
                    }
                    menuBar1.add(menu3);

                    //======== menu6 ========
                    {
                        menu6.setText("Modifer");

                        //======== menu7 ========
                        {
                            menu7.setText("Vue");

                            //======== menu8 ========
                            {
                                menu8.setText("Disposition");

                                //---- menuItem2 ----
                                menuItem2.setText("1");
                                menu8.add(menuItem2);

                                //---- menuItem3 ----
                                menuItem3.setText("2");
                                menu8.add(menuItem3);
                            }
                            menu7.add(menu8);
                        }
                        menu6.add(menu7);
                    }
                    menuBar1.add(menu6);

                    //======== menu5 ========
                    {
                        menu5.setText("Ajouter");

                        //---- menuItem4 ----
                        menuItem4.setText("Gymnase");
                        menu5.add(menuItem4);

                        //---- menuItem5 ----
                        menuItem5.setText("Association");
                        menu5.add(menuItem5);

                        //======== menu9 ========
                        {
                            menu9.setText("Personnel");

                            //---- menuItem6 ----
                            menuItem6.setText("Gardien");
                            menu9.add(menuItem6);

                            //---- menuItem7 ----
                            menuItem7.setText("Logistique");
                            menu9.add(menuItem7);

                            //---- menuItem8 ----
                            menuItem8.setText("Administrateur");
                            menu9.add(menuItem8);
                        }
                        menu5.add(menu9);
                    }
                    menuBar1.add(menu5);
                }
                Menu.add(menuBar1);
            }
            WINContentPane.add(Menu, BorderLayout.PAGE_START);
            WIN.pack();
            WIN.setLocationRelativeTo(WIN.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - baptiste audinet
    private JFrame WIN;
    private JTabbedPane C1;
    private JPanel AG;
    private JPanel Nav;
    private JScrollPane scrollPane1;
    private JTree Arborescence;
    private JDesktopPane Frame;
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
    private JPanel AS;
    private JPanel GY;
    private JPanel panel2;
    private JPanel panel8;
    private JScrollPane scrollPane6;
    private JTable table3;
    private JScrollPane scrollPane7;
    private JList list2;
    private JPanel Menu;
    private JMenuBar menuBar1;
    private JMenu menu3;
    private JMenuItem menuItem1;
    private JMenu menu6;
    private JMenu menu7;
    private JMenu menu8;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
    private JMenu menu5;
    private JMenuItem menuItem4;
    private JMenuItem menuItem5;
    private JMenu menu9;
    private JMenuItem menuItem6;
    private JMenuItem menuItem7;
    private JMenuItem menuItem8;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
