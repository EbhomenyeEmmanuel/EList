package com.esq.todolist;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//import android.support.design.widget.FloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class MainTodoScreen extends AppCompatActivity {
    //implements OnButtonClickListener
    private static Task task;
    private RecyclerView rvTasks;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Task> listOfTask;

    Context context = MainTodoScreen.this;
    static TasksAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_screen);
        //Get intent
        Intent i = getIntent();

        // Lookup the recyclerview in activity layout(to_do_screen.xml)
        rvTasks = (RecyclerView) findViewById(R.id.recyclerView);
        rvTasks.setHasFixedSize(true);
        //Initialize the main list to be used
        listOfTask = new ArrayList<>();

        //Set linear Manager
       layoutManager = new LinearLayoutManager(this);
        //Create and implement the functionalities of a FAB
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View view) {
                final EditText taskEditText = new EditText(context);
                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setTitle("Add New Task")
                        .setMessage("What do you want to do today :)")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String taskText = String.valueOf(taskEditText.getText());
                                // Initialize tasks
                                if (taskText.length() < 1){
                                    //Create another xml file that shows motivational images
                                }else{
                                    ArrayList<Task> dummyTask;
                                       if (Task.lastTaskId == 0){
                                           dummyTask = Task.createTasksList(taskText);
                                       }else{
                                           dummyTask = adapter.createTasksList(taskText);
                                       }

                                    //Main Code
                                       listOfTask.addAll(dummyTask);
                                }
                                //Set a listener to the add button
                                OnButtonClickListener listener = new OnButtonClickListener() {
                                    //Change text of button if button was clicked
                                    @Override
                                    public void onButtonClick(Button button, int position) {
                                        button.setText(task.getDoneTask() ? "Task Done" : "Do Task");
                                        //Set color of button when clicked
                                       if (task.getDoneTask() == true){
                                          button.setBackgroundColor(Color.GREEN);
                                        }
                                    }
                                };
                                // Create adapter passing in the sample user data
                                     adapter = new TasksAdapter(listOfTask, listener);
                                     //Set up RecyclerView
                                setUpRecyclerView();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                        dialog.show();
            }
             });

                //Set vertical dividers for each list
                //rvTasks.addItemDecoration(
                     // new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        //Make an animation is list is moving
               // rvTasks.setItemAnimator(new SlideInUpAnimator());

    }

    private void setUpRecyclerView() {
        // Attach the adapter to the recyclerview to populate items
        rvTasks.setAdapter(adapter);
        // Set layout manager to position the items
        rvTasks.setLayoutManager(new LinearLayoutManager(this));
        //Attach the itemToucHelper to the RecyclerView
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeController(adapter));
        itemTouchHelper.attachToRecyclerView(rvTasks);
    }
}
