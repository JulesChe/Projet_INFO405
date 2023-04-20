import controller.CalendrierController;
import model.CalendrierModel;
import vue.CalendrierView;

import javax.swing.*;

public class CalendrierApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        CalendrierModel model = new CalendrierModel();
        CalendrierView view = new CalendrierView();
        CalendrierController controller = new CalendrierController(model, view);

        controller.addEvent("7:00 - 8:00", "");
        controller.addEvent("8:00 - 9:00", "");
        controller.addEvent("9:00 - 10:00", "");
        controller.addEvent("10:00 - 11:00", "");
        controller.addEvent("11:00 - 12:00", "");
        controller.addEvent("12:00 - 13:00", "");
        controller.addEvent("13:00 - 14:00", "");
        controller.addEvent("14:00 - 15:00", "");
        controller.addEvent("15:00 - 16:00", "");
        controller.addEvent("16:00 - 17:00", "");
        controller.addEvent("17:00 - 18:00", "");
        controller.addEvent("18:00 - 19:00", "");
        controller.addEvent("19:00 - 20:00", "");


        controller.updateView();
    }
}
