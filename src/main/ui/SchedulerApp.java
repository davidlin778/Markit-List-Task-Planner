package ui;

import model.Task;
import model.TaskCheck;


import java.io.*;
import java.util.*;


//Schedule application
public class SchedulerApp {
    private static final String TASK_CHECKS_FILE = "./data/schedule.txt";
    private TaskCheck today;
    private Scanner input;

    //Used online resource from youtube https://youtu.be/HlpWrH3CcoM
    //MODIFIES: this
    //EFFECTS: to represent object as string. (both time and job)


    //EFFECTS: runs the teller application
    public SchedulerApp() {
        runScheduler();
    }


    //MODIFIES: this
    //EFFECTS: processes user input
    public void runScheduler() {
        boolean moveForward = true;
        String command = null;

        createSchedule();

        input = new Scanner(System.in);


        while (moveForward) {
            viewMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                moveForward = false;
            } else {
                acceptCommand(command);
            }

        }
    }


    //EFFECTS: displays menu and options
    private void viewMenu() {
        System.out.println("\nWelcome to Scheduler App:");
        System.out.println("\tn -> create a new task");
        System.out.println("\tc -> clear a time");
        System.out.println("\tf -> find a time and task");
        System.out.println("\tv -> view schedule");
        System.out.println("\ts -> save schedule");
        System.out.println("\tl -> load previous schedule");
        System.out.println("\tq -> quit");
    }


    // MODIFIES: this
    // EFFECTS: processes user command
    private void acceptCommand(String command) {
        if (command.equals("n")) {
            createTask();
        } else if (command.equals("c")) {
            deleteTask();
        } else if (command.equals("f")) {
            findTask();
        } else if (command.equals("v")) {
            viewSchedule();
        } else if (command.equals("s")) {
            saveSchedule();
        } else if (command.equals("l")) {
            loadSchedule();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //MODIFIES: this
    //EFFECTS: initializes taskCheck
    private void createSchedule() {
        this.today = new TaskCheck();
        //today.displayTaskCheck();
    }


    //MODIFIES: this
    //EFFECTS: creates new task and inserts into map
    private void createTask() {
        Task appTask = new Task(0, "empty");
        System.out.println("Please set hour (24 hour clock) (0 = 12am)");
        try {
            int time = input.nextInt();
            if (appTask.validTime(time).equals("invalid")) {
                System.out.println("Invalid time");
                createTask();
            } else {
                double combined = time + setMinute() * .01;
                appTask.setTaskTime(combined);
                input.nextLine();
                System.out.println("What is your task?");
                String job = input.nextLine();
                appTask.setTaskName(job);
                today.addTask(combined, appTask);
                System.out.println("task successfully added");
            }
        } catch (InputMismatchException e) {
            System.out.println("input invalid");
            input.nextLine();
            createTask();
        }
    }

    //REQUIRES: time to be between 0-60
    //MODIFIES: this
    //EFFECTS: set minute to be between 0-60
    private int setMinute() {
        System.out.println("Set minute");
        try {
            int minute = input.nextInt();
            if ((minute >= 60) || (minute < 0)) {
                System.out.println("invalid minute, please enter between 0-60");
                input.nextLine();
                return setMinute();
            } else {

                return minute;
            }
        } catch (InputMismatchException e) {
            System.out.println("invalid minute, please input valid time");
            input.nextLine();
            return setMinute();
        }
    }


    //EFFECTS: displays all task and time
    private void viewSchedule() {

        for (Map.Entry<Double, Task> entry : today.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    //MODIFIES: this
    //EFFECTS: prompts user to input a time they wish to be deleted from map
    private void deleteTask() {
        System.out.println("what time would you like to clear up? please use . to separate hour and minute ");

        try {
            double num = input.nextDouble();
            if (today.containTask(num)) {
                today.removeTask(num);
                System.out.println("successfully cleared up time slot" + num);
            } else if ((num > 23) || (num < 0)) {
                System.out.println("invalid time");
            } else {
                System.out.println("time slot already clear");
            }
        } catch (InputMismatchException e) {
            System.out.println("invalid input, next time input number please");
            input.nextLine();
            deleteTask();
        }
    }


    //EFFECTS: prompts user to input a time and prints task for specified time
    private void findTask() {

        System.out.println("what time are you looking for? please use . to separate hour and minute");
        try {
            double num = input.nextDouble();
            if (today.containTask(num)) {
                System.out.println("Task Found! ->" + today.getTask(num));

            } else if ((num < 0) && (num > 23)) {
                System.out.println("invalid time, must be between 0-23");
            } else {
                System.out.println("no tasks found");
            }
        } catch (InputMismatchException exception) {
            System.out.println("invalid input, next time try number ");
            input.nextLine();
            findTask();
        }
    }

    // EFFECTS: saves state of schedule to schedule.txt
    private void saveSchedule() {
        try {
            today.save(today);
        } catch (IOException i) {
            i.printStackTrace();
        }
        System.out.println("Schedule successfully saved");
    }

    // MODIFIES: this
    // EFFECTS: loads the schedule that was previously saved
    private void loadList(TaskCheck yesterday) throws IOException {
        yesterday.read(yesterday);
    }

    //MODIFIES: today
    //EFFECTS: loads the schedule into the current taskCheck
    private void loadSchedule() {
        try {
            today = new TaskCheck();
            loadList(today);
            System.out.println("schedule successfully loaded and can now be viewed");
        } catch (IOException e) {
            this.today = new TaskCheck();
        }
    }

}














