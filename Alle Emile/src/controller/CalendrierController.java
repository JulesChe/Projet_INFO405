package controller;

import model.CalendrierModel;
import vue.CalendrierView;

public class CalendrierController {
    private CalendrierModel model;
    private CalendrierView view;

    public CalendrierController(CalendrierModel model, CalendrierView view) {
        this.model = model;
        this.view = view;
    }

    public void addEvent(String timeSlot, String event) {
        model.addEvent(timeSlot, event);
    }

    public void updateView() {
        view.displayEvents(model.getEvents());
    }
}
