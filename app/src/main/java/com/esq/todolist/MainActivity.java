package com.esq.todolist;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView ivWelcome = findViewById(R.id.ivWelcome);
        TextView tvSubWelcome = findViewById(R.id.tvSubWelcome);
        TextView btnget = findViewById(R.id.btnget);

        //Import fonts

        Typeface MLight = Typeface.createFromAsset(getAssets(),"fonts/MLight");
        Typeface MMedium = Typeface.createFromAsset(getAssets(),"fonts/MMedium");
        Typeface MRegular = Typeface.createFromAsset(getAssets(),"fonts/MRegular");

        //Customize fonts
        ivWelcome.setTypeface(MRegular);
        tvSubWelcome.setTypeface(MLight);
        btnget.setTypeface(MRegular);


    }
}
