import controller.WeeklyAgendaController;
import model.WeeklyAgendaModel;
import vue.WeeklyAgendaView;

import java.awt.*;

public class WeeklyAgendaApp {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                WeeklyAgendaModel modele = new WeeklyAgendaModel();
                WeeklyAgendaView view = new WeeklyAgendaView();
                WeeklyAgendaController controller = new WeeklyAgendaController(modele, view);


                modele.insertTimeSlot(0, "08:00", "09:00");
                modele.insertTimeSlot(1, "14:00", "15:00");
                modele.insertTimeSlot(1, "08:00", "09:00");


                view.frame.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}