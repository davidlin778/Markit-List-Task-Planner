package persistence;

import model.Task;
import model.TaskCheck;
import java.io.IOException;
import java.io.PrintWriter;

// Represents data that can be written to file
public interface Saveable {

    // MODIFIES: taskCheck
    // EFFECTS: writes the saveable to taskCheck
    void save(TaskCheck taskCheck) throws IOException;
}
