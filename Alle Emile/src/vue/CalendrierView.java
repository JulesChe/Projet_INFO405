package vue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;

public class CalendrierView {
    private JFrame frame;
    private JTable eventsTable;

    public CalendrierView() {
        frame = new JFrame("Calendrier");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLayout(new BorderLayout());

        String[] columnNames = {"Horaire", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        eventsTable = new JTable(tableModel);
        eventsTable.setRowHeight(50); // Hauteur des lignes ajustée ici

        // Ajustement de la largeur des colonnes
        eventsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int i = 0; i < eventsTable.getColumnCount(); i++) {
            eventsTable.getColumnModel().getColumn(i).setPreferredWidth(150);
        }

        JScrollPane scrollPane = new JScrollPane(eventsTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public void displayEvents(Map<String, String> events) {
        DefaultTableModel tableModel = (DefaultTableModel) eventsTable.getModel();

        for (Map.Entry<String, String> event : events.entrySet()) {
            String[] rowData = {event.getKey(), "", "", "", "", ""};
            String[] eventInfo = event.getValue().split(", ");

            if (eventInfo.length == 3) {
                String day = eventInfo[1].trim();
                int columnIndex = getColumnIndex(day);
                if (columnIndex != -1) {
                    rowData[columnIndex] = eventInfo[0] + " (" + eventInfo[2] + ")";
                }
            }

            tableModel.addRow(rowData);
        }
    }

    private int getColumnIndex(String day) {
        switch (day.toLowerCase()) {
            case "lundi":
                return 1;
            case "mardi":
                return 2;
            case "mercredi":
                return 3;
            case "jeudi":
                return 4;
            case "vendredi":
                return 5;
            default:
                return -1;
        }
    }
}
