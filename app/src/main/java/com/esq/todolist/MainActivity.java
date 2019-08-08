package com.esq.todolist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView ivWelcome = findViewById(R.id.ivWelcome);
        TextView tvSubWelcome = findViewById(R.id.tvAppDesc);
        TextView btnget = findViewById(R.id.btnGet);
    }

    public void goToMainTodoScreen(View view){
        Intent intent = new Intent(this, MainTodoScreen.class);
        startActivity(intent);
    }
//Add an exit dialog
    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Are you sure you want to do this? ");
        builder.setCancelable(true);
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setPositiveButton("Close!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

