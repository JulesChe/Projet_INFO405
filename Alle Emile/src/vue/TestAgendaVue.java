package vue;

import javax.swing.*;
import java.beans.PropertyVetoException;
import com.formdev.flatlaf.FlatLightLaf;
import model.WeeklyAgendaModel;


public class TestAgendaVue {

    private  AgendaVue view;
    private static WeeklyAgendaModel model;


    public TestAgendaVue(AgendaVue view, WeeklyAgendaModel model){
        this.view = view;
        this.model = model;

    }


    public static void main(String[] args) throws PropertyVetoException {
         try {
                UIManager.setLookAndFeel(new FlatLightLaf());
            } catch (Exception e) {
                e.printStackTrace();
            }
                AgendaVue agendaVue = new AgendaVue(model);
                agendaVue.showView();
            }
        }

