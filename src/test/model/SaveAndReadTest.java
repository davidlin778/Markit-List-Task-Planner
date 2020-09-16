package model;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class SaveAndReadTest {
    private TaskCheck testTaskCheck;
    private TaskCheck testLoadedTaskCheck;
    private Task swimming;
    private Task eating;

    @BeforeEach
    public void runBefore() {
        testTaskCheck = new TaskCheck();
        testLoadedTaskCheck = new TaskCheck();
        swimming = new Task(1, "swimming");
        eating = new Task (2, "eating");
        testTaskCheck.addTask(12, swimming);
        testTaskCheck.addTask(1, eating);

    }

    @Test
    public void testSaveAndRead() throws IOException {
        testTaskCheck.save(testTaskCheck);
        testLoadedTaskCheck.read(testLoadedTaskCheck);
        Task testTask = testTaskCheck.getTask(1);
        Task testLoadedTask = testLoadedTaskCheck.getTask(1);
        assertEquals(testTask.getJob(), testLoadedTask.getJob());



    }




}
