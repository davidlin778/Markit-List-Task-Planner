package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TaskCheckTest {
    // delete or rename this class!
    TaskCheck myTaskCheck;
   Task swimming = new Task( 12, "swimming");
   Task homework = new Task (13,"homework");
   Task dinner = new Task (17, "dinner");



    @BeforeEach
    void runBefore() {
        myTaskCheck = new TaskCheck();
    }



    @Test
    void testAddTask(){
        myTaskCheck.addTask(1, swimming);
        assertEquals(1,myTaskCheck.getSize());
        myTaskCheck.addTask(5,homework);
        assertEquals(2, myTaskCheck.getSize());
        myTaskCheck.addTask(3, dinner);
        assertEquals(swimming, myTaskCheck.getTask(1));
        assertEquals(dinner, myTaskCheck.getTask(3));

    }

    @Test
    void testRemoveTask() {
        myTaskCheck.addTask(1, dinner);
        assertEquals(1,myTaskCheck.getSize());
        myTaskCheck.addTask(2, homework);
        assertEquals(2,myTaskCheck.getSize());
        myTaskCheck.removeTask(2);
        assertEquals(1,myTaskCheck.getSize());
        assertFalse(myTaskCheck.containTask(2));
        assertTrue(myTaskCheck.containTask(1));
    }



    @Test
    void testRemoveAndContainTask() {
        myTaskCheck.addTask(1,dinner);
        assertTrue(myTaskCheck.containTask(1));
        assertEquals(1,myTaskCheck.getSize());
        myTaskCheck.removeTask(1);
        assertFalse(myTaskCheck.containTask(1));
        assertEquals(0,myTaskCheck.getSize());

    }


    @Test
    void testEntrySet(){
        myTaskCheck.addTask(1,swimming);
        for (Map.Entry<Double, Task> entry : myTaskCheck.entrySet()) { assertEquals(entry.getKey(), 1);}
        for (Map.Entry<Double, Task> entry: myTaskCheck.entrySet()) { assertEquals(entry.getValue(),swimming);}

    }






}