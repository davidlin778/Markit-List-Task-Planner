package ui.tools;

import ui.SchedulerAppGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.File;

public class SoundPlayerTool extends ButtonTool {
    private File sound;

    public SoundPlayerTool(SchedulerAppGUI taskCheck, JComponent j, GridBagConstraints gridBag) {
        super(taskCheck, j, gridBag);
    }

    //Source: label changer exampler
    // MODIFIES: this
    // EFFECTS: constructs a new listener object and adds it to the button
    protected void createButtonListener() {
        button.addActionListener(new SoundPlayerActionListener());
    }

    // EFFECTS: sets the label of button to "Press for Motivation :D"
    @Override
    protected String setLabel() {
        return "Press for Motivation :D";
    }

    private class SoundPlayerActionListener implements ActionListener {

        //source: https://www.youtube.com/watch?v=Gyu82WG_edM
        // EFFECTS: plays clapping sound effect and prints message when button is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            taskCheck.setTxtArea("''Shoot for the moon. Even if you miss," + "\n you'll land among the stars.''" + "\n"
                    + "\n  - Norman Vincent Peale");
            sound = new File("Ontiva.com_Audience_Clapping_-_Sound_Effect.wav");
            taskCheck.playSound(sound);
        }
    }
}
