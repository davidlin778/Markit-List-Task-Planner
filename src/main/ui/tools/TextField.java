package ui.tools;

import ui.SchedulerAppGUI;

import java.awt.*;
import javax.swing.*;

public abstract class TextField {
    SchedulerAppGUI taskCheck;
    JTextField textField;

    public TextField(SchedulerAppGUI taskCheck, JComponent j, GridBagConstraints gridBag) {
        this.taskCheck = taskCheck;
        textField = new JTextField(20);
        textField.setFont(taskCheck.textFieldFont);
        createFieldListener();
        addToParent(j, gridBag);
    }

    // MODIFIES: parent
    // EFFECTS: add the text field to the parent component with the given constraints
    private void addToParent(JComponent j, GridBagConstraints gridBag) {
        j.add(textField, gridBag);
    }

    // EFFECTS: creates and adds listener to textfield
    protected abstract void createFieldListener();

    // EFFECTS: return the string in the text field
    public String getFieldText() {
        return textField.getText();
    }

    // MODIFIES: this
    // EFFECT: sets the text field to empty
    public void setEmpty() {
        textField.setText("");
    }

}
