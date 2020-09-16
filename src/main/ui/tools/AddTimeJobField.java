package ui.tools;

import ui.SchedulerAppGUI;

import java.awt.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.*;

//UI Tools package help from midsynth/ drawing player examples

public class AddTimeJobField extends TextField {

    public AddTimeJobField(SchedulerAppGUI taskCheck, JComponent j, GridBagConstraints gridBag) {
        super(taskCheck, j, gridBag);
    }

    // MODIFIES: this
    // EFFECTS: sets text field to visible
    public void setVisible(boolean b) {
        textField.setVisible(b);
    }

    // MODIFIES: this
    // EFFECTS: creates a listener and implements into the text field
    @Override
    protected void createFieldListener() {
        textField.getDocument().addDocumentListener(new HourFieldDocumentListener());
    }

    private class HourFieldDocumentListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent documentEvent) {
            visible();
        }

        @Override
        public void removeUpdate(DocumentEvent documentEvent) {
            visible();
        }

        @Override
        public void changedUpdate(DocumentEvent documentEvent) {
            visible();
        }

        // MODIFIES: taskCheck
        // EFFECTS: makes enter button visible when fields are not empty
        public void visible() {

//            taskCheck.enter.setEnabled(true);
            taskCheck.enter.setVisible(true);

        }
    }
}
