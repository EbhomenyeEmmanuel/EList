package com.esq.todolist;

import android.view.View;

import java.util.ArrayList;

public class Task {
    String taskName;
    //doneTask was made static because it had to be changed when the user clicked the button
   static Boolean doneTask = false;


    public Task(String taskName, Boolean doneTask ){
        this.taskName = taskName;
        this.doneTask = doneTask;
    }

    public String getTaskName() {
        return taskName;
    }

    public Boolean getDoneTask() {
        return doneTask;
    }

    public static int lastTaskId = 0;
    //Used to check the values of the button when clicked
    public  static int buttonChecker;

    public static ArrayList<Task> createTasksList(String taskText){
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task(taskText , doneTask));
        ++lastTaskId;
        return tasks;
    }

}
