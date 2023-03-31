package com.example.appgym.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.appgym.ListviewtestActivity;
import com.example.appgym.R;
import com.example.appgym.UserProfileActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView backButton, userButton;

    private RelativeLayout absRelaytive, chestRelaytive, armRelaytive, legRelaytive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getView();

        String name1 = getIntent().getStringExtra("username");
        // Nut Logout
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // User Button
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, UserProfileActivity.class);

                i.putExtra("username",name1);
                startActivity(i);
            }
        });
        // Abs Workout

        absRelaytive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListviewtestActivity.class);
                startActivity(i);
            }
        });

        chestRelaytive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListviewtestActivity.class);
                startActivity(i);
            }
        });

        armRelaytive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListviewtestActivity.class);
                startActivity(i);
            }
        });

        legRelaytive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ListviewtestActivity.class);
                startActivity(i);
            }
        });
    }
    public void getView() {
        backButton = findViewById(R.id.imageViewBackButton);
        userButton = findViewById(R.id.imageViewUser);

        absRelaytive = findViewById(R.id.absWorkout);
        chestRelaytive = findViewById(R.id.chestWorkout);
        armRelaytive = findViewById(R.id.armWorkout);
        legRelaytive = findViewById(R.id.legWorkout);

    }
}