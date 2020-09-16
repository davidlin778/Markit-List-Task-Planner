package ui.tools;

import ui.SchedulerAppGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.IOException;

//https://stackoverflow.com/questions/19756710/how-to-add-an-actionlistener-as-a-separate-class-in-java
public class LoadTaskTool extends ButtonTool {

    public LoadTaskTool(SchedulerAppGUI taskCheck, JComponent j, GridBagConstraints gridBag) {
        super(taskCheck, j, gridBag);
    }

    //MODIFIES: this
    // EFFECTS: constructs a new listener object and adds it to the button
    @Override
    protected void createButtonListener() {
        button.addActionListener(new LoadTaskActionListener());
    }

    // EFFECTS: sets the label to "Load Previously Saved Schedule:
    @Override
    protected String setLabel() {
        return "Load Previously Saved Schedule";
    }

    private class LoadTaskActionListener implements ActionListener {

        // EFFECTS: print all tasks when button is pressed
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                taskCheck.loadSchedule();
                taskCheck.setTxtArea("schedule successfully loaded and can now be viewed");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

    }
}
