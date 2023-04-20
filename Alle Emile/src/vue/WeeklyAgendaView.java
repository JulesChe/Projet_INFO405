package vue;

import model.WeeklyAgendaModel;

import javax.swing.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class WeeklyAgendaView {
    public JFrame frame;
    private JLabel weekLabel;
    private JPanel tasksPanel;

    private WeeklyAgendaModel modele;

    public WeeklyAgendaView() {
        initComponents();
    }

    private void initComponents() {
        frame = new JFrame("Agenda hebdomadaire");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel topPanel = new JPanel();
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        topPanel.setLayout(new BorderLayout(0, 0));

        weekLabel = new JLabel();
        topPanel.add(weekLabel, BorderLayout.CENTER);

        tasksPanel = new JPanel(new GridLayout(1, 7));
        frame.getContentPane().add(tasksPanel, BorderLayout.CENTER);


    }

    public void setWeekLabel(String weekText) {
        weekLabel.setText(weekText);
    }

    public void setTasksPanel(JPanel newTasksPanel) {
        tasksPanel.removeAll();
        tasksPanel.add(newTasksPanel);
        tasksPanel.revalidate();
        tasksPanel.repaint();
    }

    public void showView() {
        frame.setVisible(true);
    }

    public void addPreviousWeekButtonActionListener(ActionListener listener) {
        JButton previousWeekButton = new JButton("Semaine précédente");
        previousWeekButton.addActionListener(listener);
        JPanel topPanel = (JPanel) weekLabel.getParent();
        topPanel.add(previousWeekButton, BorderLayout.WEST);
    }

    public void addNextWeekButtonActionListener(ActionListener listener) {
        JButton nextWeekButton = new JButton("Semaine suivante");
        nextWeekButton.addActionListener(listener);
        JPanel topPanel = (JPanel) weekLabel.getParent();
        topPanel.add(nextWeekButton, BorderLayout.EAST);
    }

    public JPanel getTasksPanel() {
        return tasksPanel;
    }
    public JFrame getFrame() {
        return frame;
    }


}