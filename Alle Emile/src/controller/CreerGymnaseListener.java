package controller;

import crud.CrudGymnaseDAO;
import model.Gymnase;
import model.WeeklyAgendaModel;
import vue.CreateGymnaseFrame;
import vue.WeeklyAgendaView;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreerGymnaseListener {
    private List<Gymnase> gymnases = new ArrayList<>();
    private static WeeklyAgendaModel model;

    private WeeklyAgendaController controller;

    private JDesktopPane frame;
    private JPanel AG;



    public CreerGymnaseListener(JDesktopPane frame, WeeklyAgendaModel model, JPanel AG,WeeklyAgendaController controller) throws SQLException {
        this.frame = frame;
        this.model = model;
        this.AG = AG;
        this.controller = controller;


        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/allezemile", "allezemile", "nT7");
        CrudGymnaseDAO gymnaseDAO = new CrudGymnaseDAO(conn);
        gymnases = gymnaseDAO.getGymnase();


    }


    public List<Gymnase> getGymnasesFromDB() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/allezemile", "allezemile", "nT7");
        CrudGymnaseDAO gymnaseDAO = new CrudGymnaseDAO(conn);
        return gymnaseDAO.getGymnase();
    }

    public Gymnase creerGymnase(String gymnaseName, String agendaLieu) throws SQLException {
        Gymnase nouveauGymnase = new Gymnase(gymnaseName, agendaLieu);
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/allezemile", "allezemile", "nT7");
        CrudGymnaseDAO gymnaseDAO = new CrudGymnaseDAO(conn);
        gymnaseDAO.create(nouveauGymnase);
        gymnases.add(nouveauGymnase);
        return nouveauGymnase;

    }





    public void handleGymnaseSelection(String gymnaseName) throws SQLException, PropertyVetoException {
        Gymnase gymnaseSelectionne = null;

        // Récupérez les gymnases depuis la base de données
        List<Gymnase> gymnases = getGymnasesFromDB();

        for (Gymnase g : gymnases) {
            if (g.getName().equals(gymnaseName)) {
                gymnaseSelectionne = g;
                break;
            }
        }

        if (gymnaseSelectionne != null) {
            // Gymnase sélectionné
            System.out.println("Gymnase sélectionné: " + gymnaseSelectionne.getNom() + "INFO : " + gymnaseSelectionne.getLieux());
            CreateGymnaseFrame nouveauCalendrier = new CreateGymnaseFrame(model, controller );



            nouveauCalendrier.getCalendrier1().setVisible(true);
            nouveauCalendrier.getCalendrier1().setClosable(true);
            nouveauCalendrier.getCalendrier1().setMaximum(true);
            nouveauCalendrier.getCalendrier1().setMaximizable(true);
            nouveauCalendrier.getCalendrier1().setResizable(true);
            nouveauCalendrier.getCalendrier1().setPreferredSize(new Dimension(500, 500));
            nouveauCalendrier.getCalendrier1().setTitle(gymnaseSelectionne.getNom());

            nouveauCalendrier.getCalendrier1().setBounds(0, 0, 800, 505);
            frame.add(nouveauCalendrier.getCalendrier1(), JLayeredPane.DEFAULT_LAYER);
            AG.add(frame, BorderLayout.CENTER);






        } else {
            System.out.println("Aucun gymnase correspondant trouvé");
        }
    }

}
