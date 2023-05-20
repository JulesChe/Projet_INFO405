package vue;

import javax.swing.*;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

import com.formdev.flatlaf.FlatLightLaf;
import model.WeeklyAgendaModel;


public class TestAgendaVue {

    private  AgendaVueFinal view;
    private static WeeklyAgendaModel model;


    public TestAgendaVue(AgendaVueFinal view, WeeklyAgendaModel model){
        this.view = view;
        this.model = model;

    }

    public static void main(String[] args) throws PropertyVetoException, SQLException {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Login loginView = new Login();
    }

}
