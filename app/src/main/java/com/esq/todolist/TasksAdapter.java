package com.esq.todolist;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> implements ItemTouchHelperAdapter{
    // Store a member variable for the task to be performed
    private List<Task> mTasks;
    private OnButtonClickListener mListener;
    //Make the TaskAdapter's constructor receive an object that would implement the OnButtonClickListener interface

    // Pass in the contact array into the constructor
    public TasksAdapter(List<Task> tasks, OnButtonClickListener listener) {
        mTasks = tasks;
        this.mListener = listener;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public TasksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_task, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView, mListener);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(TasksAdapter.ViewHolder viewHolder, final int position) {
        // Get the data model based on position
        Task task = mTasks.get(position);

        // Set item views based on your views and data model
        // mtasks.get(position).getName(),
        TextView textView = viewHolder.taskName;
        textView.setText(task.getTaskName());
        final Button button = viewHolder.taskCheckButton;
        //Set the default value for Button
        button.setText(String.format(Locale.getDefault(), "%s","Do Task"));
        //Bind button with listener
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                //The default value of buttonChecker is 0 i.e task has not been done
                //Increment the Values of ButtonChecker
                ++Task.buttonChecker;
                if( Task.buttonChecker > 2){
                    Task.buttonChecker = 1;
                }
                //if the value of buttonChecker is 0, the task has not been done
                if (Task.buttonChecker % 2 == 0){
                    Task.doneTask = false;
                }//if the value of buttonChecker is 1, the task has been done
                else if (Task.buttonChecker % 2 == 1){
                    Task.doneTask = true;
                }
                //Bind the text of the Button based on its position in the list
                button.setText(mTasks.get(position).getDoneTask() ? "Task Done" : "Do Task");
               // if (mTasks.get(position).getDoneTask() == true){
                   // button.setBackgroundColor(Color.GREEN);
               // }
            }
        });
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mTasks != null ? mTasks.size() : 0;
    }

    public ArrayList<Task> createTasksList(String taskText){
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task(taskText , Task.doneTask));
        this.notifyItemInserted(getItemCount());
        //this.notifyDataSetChanged();
        return tasks;
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access

    //Make the viewHolder implement OnClickListener
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            // Your holder should contain a member variable for any view that will be set as you render a row
            public TextView taskName;
            public Button taskCheckButton;
            private OnButtonClickListener mListener;

            // We also create a constructor that accepts the entire item row and does the view lookups to find each subview
            public ViewHolder(View itemView, OnButtonClickListener listener) {
                // Stores the itemView in a public final member variable that can be used to access the context from any ViewHolder instance.
                super(itemView);
                mListener = listener;
                taskName = (TextView) itemView.findViewById(R.id.task_name);
                taskCheckButton = (Button) itemView.findViewById(R.id.check_task_button);
                //Set the listener on the view
                taskCheckButton.setOnClickListener(this);
            }

            public void onClick(View view ){
                mListener.onButtonClick(taskCheckButton, getAdapterPosition());
            }

        }

    @Override
    public void onItemDismiss(int position) {
        mTasks.remove(position);
        //Notify Adapter when items are removed
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mTasks, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mTasks, i, i - 1);
            }
        }
        //Notify Adapter when items are moved
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }
    //implement the deleteItem() called in SwipeCOntroller
    /*In this method we will first save the removed item into a member variable to be used in case the user wants to undo the delete.
    * We’ll also add a method call to show a Snackbar that we will implement in the next step.
    * Finally, remove the item from our list and update the adapter.
    * */
    static Task mRecentlyDeletedItem;
    static int mRecentlyDeletedItemPosition;
    public void deleteItem(int position) {
      mRecentlyDeletedItem = mTasks.get(position);
      mRecentlyDeletedItemPosition = position;
        mTasks.remove(position);
        notifyItemRemoved(position);
        showUndoSnackbar();
    }
/*Once an item is deleted our undo Snackbar should appear.
*We want the Snackbar’s action to add the recently removed item back to the list and update the adapter.
 */
    private void showUndoSnackbar() {
        View view = MainTodoScreen.findViewById(R.id.coordinator_layout);
        Snackbar snackbar = Snackbar.make(view, R.string.snack_bar_text,
                Snackbar.LENGTH_LONG);
        snackbar.setAction(R.string.snackbar_undo, new Snackbar().undoDelete().show();
    }

    private void undoDelete() {
        mTasks.add(mRecentlyDeletedItemPosition,
                mRecentlyDeletedItem);
        notifyItemInserted(mRecentlyDeletedItemPosition);
    }
}
