import controller.WeeklyAgendaController;
import model.WeeklyAgendaModel;
import vue.Login;
import vue.WeeklyAgendaView;

import javax.security.auth.login.LoginContext;
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

                Login viewCo = new Login();

                viewCo.setVisible(true);


            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}