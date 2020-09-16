package ui.tools;

import ui.SchedulerAppGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SaveTaskTool extends ButtonTool {

    public SaveTaskTool(SchedulerAppGUI taskCheck, JComponent j, GridBagConstraints gridBag) {
        super(taskCheck, j, gridBag);
    }

    //MODIFIES: this
    // EFFECTS: creates new listener object and adds to button
    @Override
    protected void createButtonListener() {
        button.addActionListener(new SaveTaskActionListener());
    }

    // EFFECTS: sets label to "Save Schedule"
    @Override
    protected String setLabel() {
        return "Save Schedule";
    }

    private class SaveTaskActionListener implements ActionListener {

        // EFFECTS: when button pressed, print all current items
        @Override
        public void actionPerformed(ActionEvent e) {
            taskCheck.saveTask();
            taskCheck.setTxtArea("schedule successfully saved");
        }
    }
}