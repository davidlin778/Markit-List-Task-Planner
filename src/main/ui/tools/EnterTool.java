package ui.tools;

import model.Task;
import ui.SchedulerAppGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EnterTool extends Tool {
    private Task task;

    public EnterTool(SchedulerAppGUI taskCheck, JComponent j, GridBagConstraints gridBag) {
        super(taskCheck, j, gridBag);
    }

    // MODIFIES: this
    // EFFECTS: creates a new button and sets the visibility of the button to false
    @Override
    public void button() {
        button = new JButton("Enter");

        button.setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: creates new listener object and adds to button
    @Override
    protected void createButtonListener() {
        button.addActionListener(new EnterActionListener());
    }

    private class EnterActionListener implements ActionListener {

        // MODIFIES: taskCheck
        // EFFECTS: when button is pressed, get data from the fields in the panel and construct
        //          an item based on the data, and reset text field and combobox
        @Override
        public void actionPerformed(ActionEvent e) throws NumberFormatException {

            try {
                parseAndSet();
                setOption();
            } catch (NumberFormatException n) {
                taskCheck.setTxtArea("invalid entry. please make sure to enter valid time with no spaces");
            }
        }
    }

    //EFFECTS: parse and sets hour, minute and job for ADD and REMOVE
    public void parseAndSet() {
        int time = Integer.parseInt(taskCheck.getHourName());
        int minute = Integer.parseInt(taskCheck.getMinute());
        String selected = taskCheck.getSelected();
        String job = taskCheck.getJobName();


        if (selected.equals(taskCheck.ADD)) {
            task = new Task(0, "");
            if ((minute > 59) || (minute < 0) || (time > 23) || (time < 0)) {
                taskCheck.setTxtArea("invalid time, please enter hour between 0-23, and minute between 00-59");
            } else {
                task.setTaskName(job);
                task.setTaskTime(time + 0.01 * minute);
                taskCheck.addTask(time + 0.01 * minute, task);
            }
        } else if (selected.equals(taskCheck.REMOVE)) {
            if (taskCheck.containsTask(time + 0.01 * minute)) {
                taskCheck.removeTask(time + 0.01 * minute);
            } else {
                taskCheck.setTxtArea("time is already free");
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: sets the fields to empty
    public void setOption() {
        taskCheck.setHourField();
        taskCheck.setJobField();
        taskCheck.setMinuteField();
        taskCheck.setOptionType();
    }
}
