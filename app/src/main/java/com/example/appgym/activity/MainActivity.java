package com.example.appgym.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.appgym.activity.workout.AbsviewActivity;
import com.example.appgym.R;
import com.example.appgym.activity.workout.ArmviewActivity;
import com.example.appgym.activity.workout.ChestActivity;
import com.example.appgym.activity.workout.ChestviewActivity;
import com.example.appgym.activity.workout.LegviewActivity;

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
                Intent i = new Intent(MainActivity.this, AbsviewActivity.class);
                startActivity(i);
            }
        });

        chestRelaytive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ChestviewActivity.class);
                startActivity(i);
            }
        });

        armRelaytive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ArmviewActivity.class);
                startActivity(i);
            }
        });

        legRelaytive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LegviewActivity.class);
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