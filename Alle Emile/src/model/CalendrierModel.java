package model;

import java.util.HashMap;
import java.util.Map;

public class CalendrierModel {
    private Map<String, String> events;

    public CalendrierModel() {
        events = new HashMap<>();
    }

    public Map<String, String> getEvents() {
        return events;
    }

    public void addEvent(String timeSlot, String event) {
        events.put(timeSlot, event);
    }
}
