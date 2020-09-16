package model;

//Represents a task that has a time and task
public class Task {
    private String job;
    private double time;


    //EFFECTS: Task has given time and and job
    public Task(double time, String job) {
        this.time = time;
        this.job = job;
    }

    //MODIFIES: this
    //EFFECTS: Sets a task name
    public void setTaskName(String job) {
        this.job = job;
    }

    //REQUIRES: time slot to be open
    //MODIFIES: this
    //EFFECTS: Sets a task time
    public void setTaskTime(double time) {
        this.time = time;
    }

    //EFFECTS: return time
    public double getTime() {
        return time;
    }

    //EFFECTS: returns job
    public String getJob() {
        return job;
    }


    //Source: from teller application
    //MODIFIES: this
    //EFFECTS: returns a string representation of account
    public String toString() {
        return  "time = " + time + " " + "job = " + job;
    }


    //EFFECTS: returns invalid if integer is greater than 23 or less than 0. otherwise returns valid
    public String validTime(Integer num) {
        if ((num > 23) || (num < 0)) {
            return "invalid";
        }
        return "valid";
    }
}

