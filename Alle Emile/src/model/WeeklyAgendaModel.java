package model;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import java.time.temporal.ChronoUnit;

public class WeeklyAgendaModel {
    private LocalDate startOfWeek;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.FRENCH);
    private static final String[] WEEK_DAYS = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};
    private static final String[] TIME_SLOTS = {
            "05:00", "06:00", "07:00",
            "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00",
            "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"
    };

    private List<DefaultListModel<String>> daysTimeSlots;


    public WeeklyAgendaModel() {
        startOfWeek = LocalDate.now().with(java.time.DayOfWeek.MONDAY);
    }

    public LocalDate getStartOfWeek() {
        return startOfWeek;
    }

    public String[] getWeekDays() {
        return WEEK_DAYS;
    }

    public String[] getTimeSlots() {
        return TIME_SLOTS;
    }

    public String getFormattedStartDate() {
        return DATE_FORMATTER.format(startOfWeek);
    }

    public String getFormattedEndDate() {
        return DATE_FORMATTER.format(startOfWeek.plus(6, ChronoUnit.DAYS));
    }

    public void goToPreviousWeek() {
        startOfWeek = startOfWeek.minus(1, ChronoUnit.WEEKS);
    }

    public void goToNextWeek() {
        startOfWeek = startOfWeek.plus(1, ChronoUnit.WEEKS);
    }

    public String getWeekDay(int index) {
        if (index < 0 || index >= WEEK_DAYS.length) {
            throw new IllegalArgumentException("Invalid index for week day");
        }
        return WEEK_DAYS[index];
    }

    public String getFormattedDateAt(int index) {
        if (index < 0 || index >= 7) {
            throw new IllegalArgumentException("Invalid index for date");
        }
        LocalDate date = startOfWeek.plus(index, ChronoUnit.DAYS);
        return DATE_FORMATTER.format(date);
    }

    public void insertTimeSlot(int dayIndex, String newTimeSlot, String endTime) {
        if (dayIndex < 0 || dayIndex >= 7) {
            throw new IllegalArgumentException("Index invalide pour le jour");
        }

        DefaultListModel<String> taskListModel = daysTimeSlots.get(dayIndex);
        int index = 0;
        for (; index < taskListModel.size(); index++) {
            String existingTimeSlot = taskListModel.getElementAt(index).substring(0, 5);
            if (newTimeSlot.compareTo(existingTimeSlot) < 0) {
                break;
            }
        }
        taskListModel.add(index, newTimeSlot + " - ");
    }







}