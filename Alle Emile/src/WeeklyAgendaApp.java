import controller.WeeklyAgendaController;
import model.WeeklyAgendaModel;
import vue.WeeklyAgendaView;

import java.awt.*;

public class WeeklyAgendaApp {

    private WeeklyAgendaModel model;
    private WeeklyAgendaView view;
    private WeeklyAgendaController controller;

    public WeeklyAgendaApp(WeeklyAgendaModel model, WeeklyAgendaView view, WeeklyAgendaController controller) {
        this.model = model;
        this.view = view;
        this.controller = controller;
    }

    public void run() {
        EventQueue.invokeLater(() -> {
            try {
                view.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                WeeklyAgendaModel modele = new WeeklyAgendaModel();
                WeeklyAgendaView view = new WeeklyAgendaView();
                WeeklyAgendaController controller = new WeeklyAgendaController(modele, view);


                modele.insertTimeSlot(0, "08:00", "09:00","Badminton");
                modele.insertTimeSlot(1, "14:00", "15:00","Tennis");
                modele.insertTimeSlot(1, "08:00", "09:00","Basketball");


                view.frame.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}