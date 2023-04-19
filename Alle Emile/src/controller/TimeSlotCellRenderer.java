package controller;

import javax.swing.*;
import java.awt.*;

class TimeSlotCellRenderer extends JLabel implements ListCellRenderer<String> {
    @Override
    public Component getListCellRendererComponent(JList<? extends String> list, String task, int index, boolean isSelected, boolean cellHasFocus) {
        setText(task);
        setOpaque(true);
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return this;
    }
}