package ui.tools;

import ui.SchedulerAppGUI;

import java.awt.*;
import javax.swing.*;


public abstract class ButtonTool extends Tool {

    public ButtonTool(SchedulerAppGUI taskCheck, JComponent j, GridBagConstraints gridBag) {
        super(taskCheck, j, gridBag);
    }

    // MODIFIES: this
    // EFFECTS: creates a button obejct
    @Override
    protected void button() {
        button = new JButton(setLabel());
    }

    // EFFECTS: sets the name of the label for the button
    protected abstract String setLabel();
}
