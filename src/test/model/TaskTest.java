package model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    // delete or rename this class!
   Task swimming = new Task( 12, "swimming");
   Task homework = new Task (13,"homework");
   Task dinner = new Task (17, "dinner");
  // Task sleep = new Task (22, "sleep");




    @Test
    void testSetTaskName(){
        Task testTask = new Task(0,"empty");
        assertEquals("empty", testTask.getJob());
        testTask.setTaskName("party");
        assertEquals("party", testTask.getJob());

    }

    @Test
    void testSetTaskTime(){
        assertEquals(12 ,swimming.getTime());
        swimming.setTaskTime(13);
        assertEquals(13, swimming.getTime());

    }

    @Test
    void testToString() {
       assertEquals( "time = 13.0 job = homework",homework.toString());
       assertEquals("time = 17.0 job = dinner",dinner.toString());


    }

    @Test
    void testValidTime(){
        assertEquals("valid",swimming.validTime(0));
        assertEquals("invalid",swimming.validTime(-1));
        assertEquals("valid",swimming.validTime(23));
        assertEquals("invalid",swimming.validTime(24));

    }





}