/*
 * Created by JFormDesigner on Mon May 08 14:01:05 CEST 2023
 */

package vue;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.*;

import controller.*;
import model.WeeklyAgendaModel;

import java.awt.*;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;


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


public class AgendaVueFinal  {

    private JLabel weekLabel;
    private WeeklyAgendaModel modele;
    public AgendaVueFinal(WeeklyAgendaModel modele) throws PropertyVetoException, SQLException {
        this.modele = modele;
        initComponents();
    }

    private void initComponents() throws PropertyVetoException, SQLException {
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
                            EmptyBorder( 0, 0, 0, 0) , "", javax. swing. border. TitledBorder. CENTER, javax. swing
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
                            // Créez une instance de CreerGymnaseController
                            CreerGymnaseListener controller = new CreerGymnaseListener(Frame, modele, AG );
                            //---- Arborescence ----

                            //---- Arborescence ----
                            Arborescence.setMinimumSize(new Dimension(150, 0));
                            DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Gymnases");
                            for (Gymnase g : controller.getGymnasesFromDB()) {
                                rootNode.add(new DefaultMutableTreeNode(g.getName()));
                            }
                            Arborescence.setModel(new DefaultTreeModel(rootNode));

                            Arborescence.setMaximumSize(new Dimension(200, 56));
                            Arborescence.setPreferredSize(new Dimension(150, 56));
                            scrollPane1.setViewportView(Arborescence);

                            Arborescence.addTreeSelectionListener(new TreeSelectionListener() {
                                @Override
                                public void valueChanged(TreeSelectionEvent e) {
                                    DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                                            Arborescence.getLastSelectedPathComponent();

                                    if (node == null) return; // Aucun nœud n'est sélectionné

                                    if (node.isLeaf()) { // Vérifier si le nœud est une feuille
                                        String nodeName = node.toString();
                                        // Appel à la méthode du contrôleur
                                        try {
                                            controller.handleGymnaseSelection(nodeName);
                                        } catch (SQLException | PropertyVetoException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                    }
                                }
                            });
                        }
                        Nav.add(scrollPane1, BorderLayout.CENTER);
                    }
                    AG.add(Nav, BorderLayout.WEST);

                    //======== Frame ========
                    {


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
                        // Ajouter un ActionListener à votre bouton 'Ajouter'
                        // Ajouter un ActionListener à votre bouton 'Ajouter'
                        CreerGymnaseListener controller = new CreerGymnaseListener(Frame, modele, AG);
                        menuItem4.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                // Affiche une boîte de dialogue d'entrée pour obtenir le nom de l'agenda
                                String gymnaseName = JOptionPane.showInputDialog(null, "Veuillez entrer le nom du gymnase :");
                                String gymnaseLieu = JOptionPane.showInputDialog(null, "Veuillez entrer le lieu du gymnase :");


                                // Crée un nouveau gymnase avec le nom de l'agenda
                                Gymnase nouveauGymnase = null;
                                try {
                                    nouveauGymnase = controller.creerGymnase(gymnaseName, gymnaseLieu);
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                                DefaultTreeModel model = (DefaultTreeModel) Arborescence.getModel();
                                DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
                                root.add(new DefaultMutableTreeNode(nouveauGymnase.getName()));
                                model.reload();  // Ceci informe le modèle que sa structure a changé

                            }
                        });



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

    public void showView() {
        WIN.setVisible(true
        );
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
