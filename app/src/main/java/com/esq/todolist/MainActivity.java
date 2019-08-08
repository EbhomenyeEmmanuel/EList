package com.esq.todolist;

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
        TextView tvSubWelcome = findViewById(R.id.tvSubWelcome);
        TextView btnget = findViewById(R.id.btnget);

    }

    public void goToMainTodoScreen(View view){
        Intent intent = new Intent(this, MainTodoScreen.class);
        startActivity(intent);
    }
}
