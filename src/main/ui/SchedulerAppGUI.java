package ui;

import model.Task;
import model.TaskCheck;
import ui.tools.*;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SchedulerAppGUI extends JFrame {

    private static final int WIDTH = 950;
    private static final int HEIGHT = 500;
    public static final String DEFAULT = "--Select Option--";
    public static final String ADD = "Add Task";
    public static final String REMOVE = "Remove Task";

    public TaskCheck today;
    private JTextArea textArea;
    public Font textFieldFont = new Font("Courier BOLD", Font.PLAIN, 18);
    private JPanel toolArea;
    private List<Tool> tools = new ArrayList<>();
    private JComboBox option;
    private JLabel hour;
    private JLabel minute;
    private JLabel jobName;
    private AddTimeJobField jobField;
    private AddTimeJobField hourField;
    private AddTimeJobField minuteField;
    public EnterTool enter;
    private String[] types = {DEFAULT, ADD, REMOVE};
    private File sound;
    private GridBagConstraints setConstraints;


    //Source: Youtube C418 - Sweden
    // EFFECTS: creates the window for the to do list
    public SchedulerAppGUI() throws IOException {
        super("Scheduler App");
        today = new TaskCheck();

        startGUI();
        sound = new File("Ontiva.com_C418_-_Sweden_-_Minecraft_Volume_Alpha (3).wav");
        playSoundContinuous(sound);
    }

    // MODIFIES: this
    // EFFECTS: draws the JFrame window for the to do list, and makes the tools that will work on the to do list
    private void startGUI() {
        setLayout(new BorderLayout());
        createTextArea();
        createToolArea();
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: creates text area
    private void createTextArea() {
        textArea = new JTextArea();
        textArea.setFont(textFieldFont);


        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        textArea.setEditable(false);
        Color c2 = new Color(189, 239, 235);
        textArea.setBackground(c2);
        viewTaskCheck(today);
        add(textArea, BorderLayout.CENTER);
    }

    //Source: how to use gridbag : https://www.youtube.com/watch?v=g2vDARb7gx8
    // MODIFIES: this
    // EFFECTS: creates the tool area of GUI
    private void createToolArea() {
        Container container = getContentPane();
        toolArea = new JPanel();
        toolArea.setLayout(new GridBagLayout());
        toolArea.setPreferredSize(new Dimension(WIDTH - 500, HEIGHT));
        container.add(toolArea, BorderLayout.WEST);
        Color c = new Color(0, 0, 153);
        toolArea.setBackground(c);

        createButtonTools();

    }


    //EFFECTS: initializes all the buttons for the tools
    private void createButtonTools() {
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.weighty = 0.1;

        JPanel addPanel = new JPanel();
        addNewPanel(addPanel);
        toolArea.add(addPanel, gridBag);

        gridBag.gridy = 1;
        ButtonTool viewTasks = new ViewTaskTool(this, toolArea, gridBag);
        tools.add(viewTasks);

        gridBag.gridy = 2;
        ButtonTool saveTasks = new SaveTaskTool(this, toolArea, gridBag);
        tools.add(saveTasks);

        gridBag.gridy = 3;
        ButtonTool loadTasks = new LoadTaskTool(this, toolArea, gridBag);
        tools.add(loadTasks);

        gridBag.gridy = 4;
        ButtonTool playMusic = new SoundPlayerTool(this, toolArea, gridBag);
        tools.add(playMusic);
    }


    // MODIFIES: this
    // EFFECTS: creates the hour, minute, and job label and field
    private void addNewPanel(JPanel newPanel) {
        newPanel.setLayout(new GridBagLayout());
        panelActionPerformed();
        setConstraints = new GridBagConstraints();

        fieldAndLabelGrids(newPanel);

        fieldAndLabelVisibility();
    }

    //EFFECTS: sets visibility of label and fields for addNewPanel
    private void fieldAndLabelVisibility() {
        falseVisibility();
    }

    private void falseVisibility() {
        hour.setVisible(false);
        hourField.setVisible(false);
        jobName.setVisible(false);
        jobField.setVisible(false);
        minute.setVisible(false);
        minuteField.setVisible(false);
    }

    //MODIFIES: this
    //EFFECTS: sets the constraints and names the label for hour, minute and job
    private void fieldAndLabelGrids(JPanel newPanel) {
        hour = new JLabel("Hour: ");
        setConstraints.gridy = 1;
        setConstraints.gridx = 1;
        newPanel.add(hour, setConstraints);

        setConstraints.gridx = 2;
        hourField = new AddTimeJobField(this, newPanel, setConstraints);


        minute = new JLabel("Minute: ");
        setConstraints.gridy = 2;
        setConstraints.gridx = 1;
        newPanel.add(minute, setConstraints);

        setConstraints.gridx = 2;
        minuteField = new AddTimeJobField(this, newPanel, setConstraints);


        jobName = new JLabel("Name of Task: ");
        setConstraints.gridy = 3;
        setConstraints.gridx = 1;
        newPanel.add(jobName, setConstraints);

        setConstraints.gridx = 2;
        jobField = new AddTimeJobField(this, newPanel, setConstraints);

        setConstraints.gridy = 0;
        newPanel.add(option, setConstraints);

        setConstraints.gridy = 4;
        enter = new EnterTool(this, newPanel, setConstraints);

    }


    //Source: https://stackoverflow.com/questions/24457607/action-listener-on-combobox-to-display-selection-in-textfield
    //https://www.youtube.com/watch?v=hSghISXr7b8
    // and Label Changer Application
    //EFFECTS: GUI
    private void panelActionPerformed() {
        option = new JComboBox(types);
        option.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                String option = (String) comboBox.getSelectedItem();
                if (option.equals(ADD)) {
                    addVisibility();
                    enter.setVisible(true);

                } else if (option.equals(REMOVE)) {
                    removeVisibility();
                    enter.setVisible(true);
                } else if (option.equals(DEFAULT)) {
                    defaultVisibility();
                    enter.setVisible(false);
                }
            }
        });
    }

    //EFFECTS: sets the visibility for the add option
    private void addVisibility() {
        hour.setVisible(true);
        hourField.setVisible(true);
        jobName.setVisible(true);
        jobField.setVisible(true);
        minute.setVisible(true);
        minuteField.setVisible(true);
    }

    //EFFECTS: sets visibility for the remove option
    private void removeVisibility() {
        hour.setVisible(true);
        hourField.setVisible(true);
        jobName.setVisible(false);
        jobField.setVisible(false);
        minute.setVisible(true);
        minuteField.setVisible(true);
    }

    //EFFECTS: sets visiblity for the default option
    private void defaultVisibility() {
        falseVisibility();
    }





    // EFFECTS: gets the type of option that the selector is set on
    public String getSelected() {
        return (String) option.getSelectedItem();
    }

    // EFFECTS: gets the text in the hour text field
    public String getHourName() {
        return hourField.getFieldText();
    }

    // EFFECTS: gets the text in the job text field
    public String getJobName() {
        return jobField.getFieldText();
    }

    // EFFECTS: gets the text in the minute field
    public String getMinute() {
        return minuteField.getFieldText();
    }

    // MODIFIES: this
    // EFFECTS: adds task to taskCheck
    public void addTask(Double d, Task t) throws NumberFormatException {
        try {
            today.addTask(d, t);
            setTxtArea("your task has been added");
        } catch (NumberFormatException e) {
            setTxtArea("invalid entry");
        }
    }

    //MODIFIES: this
    //EFFECTS: removes task from taskCheck
    public void removeTask(Double n) {
        today.removeTask(n);
        setTxtArea("your task has been removed");
    }

    // MODIFIES: this
    // EFFECTS: sets the hour field to empty
    public void setHourField() {
        hourField.setEmpty();
    }


    // MODIFIES: this
    // EFFECTS: sets the text in the text area to the given text
    public void setTxtArea(String txt) {
        textArea.setText(txt);
    }


    // MODIFIES: this
    // EFFECTS: sets the job  field to empty
    public void setJobField() {
        jobField.setEmpty();
    }

    // MODIFIES: this
    // EFFECTS: sets the date field in the add area to empty
    public void setMinuteField() {
        minuteField.setEmpty();
    }

    // MODIFIES: this
    // EFFECTS: resets the the option bar and sets the visibility fo the enter button to false
    public void setOptionType() {
        option.setSelectedIndex(0);
        enter.setVisible(false);
    }

    //EFFECTS: finds the task for the entered time. return true if found, else return false
    public boolean containsTask(Double d) {
        if (today.containTask(d)) {
            return true;
        } else {
            return false;
        }
    }


    // saves the tasks in the taskcheck
    public void saveTask() {
        try {
            today.save(today);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the schedule that was previously saved
    public void loadSchedule() throws IOException {
        today.read(today);
    }


    // EFFECTS: displays all tasks in the taskcheck
    public void viewTaskCheck(TaskCheck taskCheck) {
        String schedule = "Here are your tasks: " + toString();
        setTxtArea(schedule);
    }

    //MODIFIES: this
    //EFFECTS: converts the taskcheck to string in entryset
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Double, Task> entry : today.entrySet()) {
            sb.append("\nTime: ").append(entry.getKey()).append("  Task: ").append(entry.getValue().getJob());
        }
        return sb.toString();
    }

    //source:http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
    //MODIFIES: this
    //EFFECTS: plays the wav. audio file continuously
    public void playSoundContinuous(File soundName) {
        try {
            Clip clip = getClip(soundName);
            clip.loop(Clip.LOOP_CONTINUOUSLY);



        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    private Clip getClip(File soundName) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        Clip clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(soundName));
        clip.start();
        return clip;
    }


    //source:http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
    //MODIFIES: this
    //EFFECTS: plays the wav. audio file
    public void playSound(File soundName) {
        try {
            Clip clip = getClip(soundName);

        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }



    public static void main(String[] args) throws IOException {
        new SchedulerAppGUI();
    }
}
