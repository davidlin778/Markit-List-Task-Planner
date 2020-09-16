# My Personal Project

## Application Name: *Build On Yesterday* 

**What will this application do?**

  My application will be daily task scheduler. The user can add/delete time slots into
  the Treemap. The user will also be able to do other features such as searching and 
  viewing. 

**Who will use it?**

  The application is built for anyone who wants to make their daily lives 
more productive.  This especially include students who want to increase their 
daily productivity. 

**Why is this project of interest to you?**

  Due to the global pandemic, I am self isolating at home most of the time. 
With all this time at home, I realize that there is so much spare time every day.
Therefore, I want to create this application to help me utilize my time more efficiently
and help me accomplish more productive tasks every day. 
Studies show that living by a daily routine is a path to a more productive,
healthy and happy life. 

## User Stories
- As a user, I want to be able to add a task to my to-do list
- As a user, I want to be able to view all of my tasks on my to-do list 
- AS a user, I want to be able to view all of my tasks in order 
- As a user, I want to be able to delete a task from my to-do-list
- As a user, I want to be able to find a task from my to-do-list
- As a user, I want to be able to save my to-do list to file
- As a user, I want to be able to optionally load my to-do list from file when the program starts

##Instructions for Grader
- 1.Once project is cloned or downloaded, run SchedulerAppGUI to run application GUI (Graphical User Interface should appear and background music should start playing)

**Adding task (add an X to a Y)** 
- 2.Press on "--Select Option--" and select "Add task"
- 3.Insert integer between 0-23 for "hour", insert integer between 0-60 for "minute", and insert string for "task"
- 4.Click enter
- 5.Click "View tasks", task should now appear in text field (light blue area))
- 6.Repeat steps 2-5 if you would like to add another task

**Removing task (remove an X from a Y)**
- 7.Press on "--Select Option--" and select "Remove task"
- 8.Insert existing integer for "hour" and "minute"
- 9.Click enter
- 10.Click "View tasks", task should now be gone from text area (light blue area)
- 11.Repeat steps 7-10 if you would like to remove another task

**Playing Sound (audio component)**
- 12.Click on "Press for Motivation :D"
- 13.Clapping and cheering sound should play, then a message will appear in the text area (light blue area)
- 14.Click on "View tasks" , to return to schedule
- 15.Repeat steps 12-14 if you would like to trigger audio sound

**Save Schedule (save state of application)**
- 16.Click on "Save Schedule" to save current tasks in schedule (clicked usually before closing application)
- 17.Click on "View tasks" , to return to schedule

**Load Schedule (load state of application)**
- 16.Click on "Load Previously Saved Schedule" to load previously saved tasks to currentr schedule (clicked usually right after opening application)
- 17.Click on "View tasks" , to return to schedule

##Phase 4: 

**Phase 4: Task 2**
I was able to implement the option "Make appropriate use of the Map interface somewhere in your code." It has been implemented in the
TaskCheck class. The map used in taskCheck is used as follows: <<Double, Task>>. Both imports of java.util.Map, and java.util.TreeMap 
have also been used within the class.

**Phase 4: Task 3**

I refactored two areas in my code.

**1.** The first area that I refactored my code is in the SchedulerAppGUI class. There were two methods where I found
duplicate code. The two methods that contained the same code were defaultVisibility() and fieldAndLabelVisibility().
They had the exact same setVisibility calls for all the label and fields. Therefore I decided to extract the code into a seperate method
called falseVisibility(). I then called this method in both defaultVisibility() and fieldAndlabelVisibility() to improve/reduce coupling.

**2.** The second area that I refactored my code is also in the SchedulerAppGUI class. This process also involved 2 two methods that had duplicate
code. The two methods were playSound(File soundName) and playSoundContinuously(File soundName). Both methods contained duplicate code of calling the AudioSystem and getting
it to play the clip. Therefore I extracted the duplicate code and created a new method called getClip(File soundName). This method runs the code to play the sound and is called
in both playSound and playSoundContinuous to improve/reduce coupling.
