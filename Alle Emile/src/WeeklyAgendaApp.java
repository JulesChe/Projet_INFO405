import controller.WeeklyAgendaController;
import model.WeeklyAgendaModel;
import vue.WeeklyAgendaView;

import java.awt.*;

public class WeeklyAgendaApp {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                WeeklyAgendaModel model = new WeeklyAgendaModel();
                WeeklyAgendaView view = new WeeklyAgendaView();
                WeeklyAgendaController controller = new WeeklyAgendaController(model, view);
                view.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}