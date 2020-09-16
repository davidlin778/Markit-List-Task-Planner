package ui.tools;

import ui.SchedulerAppGUI;

import java.awt.*;
import javax.swing.*;

//UI Tools package help from midsynth/ drawing player examples
// JComponent: https://web.mit.edu/6.005/www/sp14/psets/ps4/java-6-tutorial/components.html

public abstract class Tool {
    protected JButton button;
    protected SchedulerAppGUI taskCheck;

    public Tool(SchedulerAppGUI taskCheck, JComponent j, GridBagConstraints gridBag) {
        this.taskCheck = taskCheck;
        button();
        addToParent(j, gridBag);
        createButtonListener();
    }

    // EFFECTS: constructs a button for tool
    protected abstract void button();

    //EFFECTS: creates and adds listener to button
    protected abstract void createButtonListener();

    // MODIFIES: parent
    // EFFECTS: adds the button to the parent component with the given constraints
    public void addToParent(JComponent j, GridBagConstraints gridBag) {
        j.add(button, gridBag);
    }


    // MODIFIES: this
    // EFFECTS: sets text field to visible
    public void setVisible(boolean b) {
        button.setVisible(b);
    }
}