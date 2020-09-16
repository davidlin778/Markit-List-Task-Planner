package model;



import persistence.Saveable;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map;
import java.util.TreeMap;

//Represents a schedule with 24 hour time slots assigned to a task
public class TaskCheck implements Saveable {

    private TreeMap<Double, Task> schedule;


    //EFFECTS: constructs a new hashmap for schedule
    public TaskCheck() {
        schedule = new TreeMap<Double, Task>();

    }

    //MODIFIES: this
    //EFFECTS: adds task into hashmap
    public void addTask(double num, Task task) {
        schedule.put(num, task);
    }

    //REQUIRES: task is in hashmap
    //MODIFIES: this
    //EFFECTS: removes a task from hashmap
    public void removeTask(double num) {
        schedule.remove(num);
    }

    //EFFECTS: returns true if there is task for inputted time
    public boolean containTask(double num) {
        return schedule.containsKey(num);
    }

    //EFFECTS: returns the task
    public Task getTask(double num) {
        return schedule.get(num);
    }


    //source, suggestion for intellij red light bulb
    //EFFECTS: returns the entrySet of TaskCheck
    public Iterable<? extends Map.Entry<Double, Task>> entrySet() {
        return schedule.entrySet();
    }

    //EFFECTS: returns the size of the TaskCheck
    public int getSize() {
        return schedule.size();
    }


    // MODIFIES:  text file
    // EFFECTS: saves the tasks on taskCheck to a text file
    public void save(TaskCheck list) throws IOException {
        PrintWriter writer = new PrintWriter("./data/schedule.txt", "UTF-8");
        for (Map.Entry<Double, Task> entry : schedule.entrySet()) {
            writer.println(entry.getKey() + ":" + entry.getValue().getTime() + ":" + entry.getValue().getJob());
        }
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: loads the tasks from the text file and adds them to taskCheck
    public void read(TaskCheck taskCheck) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./data/schedule.txt"));
        for (String line : lines) {
            ArrayList<String> components = splitLine(line);

            Double value = Double.parseDouble(components.get(0));
            Double time = Double.parseDouble(components.get(1));


            Task task = new Task(time, components.get(2));

            taskCheck.addTask(value, task);
        }
    }

    //Source: Teller App
    // EFFECTS: returns a list of strings obtained by splitting line on space
    private static ArrayList<String> splitLine(String line) {
        String[] splits = line.split(":");
        return new ArrayList<>(Arrays.asList(splits));
    }



}



