package ui.tools;

import ui.SchedulerAppGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class ViewTaskTool extends ButtonTool {

    public ViewTaskTool(SchedulerAppGUI taskCheck, JComponent j, GridBagConstraints gridBag) {
        super(taskCheck, j, gridBag);
    }

    //MODIFIES: this
    // EFFECTS: creates new listener for button
    @Override
    protected void createButtonListener() {
        button.addActionListener(new ViewTaskActionListener());
    }

    // EFFECTS: sets the label for button to be  "View Tasks"
    @Override
    protected String setLabel() {
        return "View Tasks";
    }

    private class ViewTaskActionListener implements ActionListener {

        // EFFECTS: print all tasks when button is pressed
        @Override
        public void actionPerformed(ActionEvent e) {
            taskCheck.viewTaskCheck(taskCheck.today);
        }
    }
}
