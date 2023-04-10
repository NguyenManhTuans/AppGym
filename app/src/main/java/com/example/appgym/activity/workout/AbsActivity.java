package com.example.appgym.activity.workout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appgym.R;

import java.util.Locale;

public class AbsActivity extends AppCompatActivity {
    TextView name;
    ImageView image;

    // Countdown Timer
    private static final long START_TIME_IN_MILLIS = 30000;

    private TextView TextViewcountDown ;
    private Button ButtonStartPause, ButtonReset;

    private CountDownTimer CountDownTimer;
    private boolean TimerRunning;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    private ImageView arrowBackButton;

    String[] exerciseNames = {
            "Bicycles", "Cobra Crunches ", "Crunches Body",
            "Crunches Reach", "Jumping Jack", "Lying Leg Raises",
            "Touch Heel", "Mountain Climber"
    };
    int[] exerciseImages = {
            R.drawable.bicycles, R.drawable.cobra_crunches,  R.drawable.crunches_body,
            R.drawable.crunches_reach, R.drawable.jumping_jack, R.drawable.lying_leg_raises,
            R.drawable.touch_heel,  R.drawable.mountain_climber
    };
    private Button btnDone, btnPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdata);

        name = findViewById(R.id.listdata);
        image = findViewById(R.id.imageView);

        Intent intent = getIntent();
        final int[] po = {intent.getIntExtra("vitri", 0)};
        name.setText(intent.getStringExtra("name"));
        image.setImageResource(intent.getIntExtra("image", 0));

        btnDone = findViewById(R.id.btnDone);
        btnPrevious = findViewById(R.id.btnPrevious);

        //Nut quay lai
        arrowBackButton = findViewById(R.id.backButton);

        arrowBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(po[0] < exerciseImages.length - 1){
                    po[0] = po[0] + 1;
                    name.setText(exerciseNames[po[0]]);
                    image.setImageResource(exerciseImages[po[0]]);
                }else {
                    finish();
                }
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(po[0] > 0){
                    po[0] = po[0] - 1;
                    name.setText(exerciseNames[po[0]]);
                    image.setImageResource(exerciseImages[po[0]]);
                }else {
                    finish();
                }
            }
        });

        // Dem nguoc thoi gian
        TextViewcountDown = findViewById(R.id.text_view_countdown);
        ButtonStartPause = findViewById(R.id.btn_start_countdown);
        ButtonReset = findViewById(R.id.btn_reset_countdown);

        ButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TimerRunning){
                    pauseTimer();
                } else{
                    startTimer();
                }
            }
        });

        ButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });

        updateCountDownText();
    }

    private void startTimer(){
        CountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                TimerRunning = false;
                ButtonStartPause.setText("Start");
                ButtonStartPause.setVisibility(View.INVISIBLE);
                ButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();
        TimerRunning = true;
        ButtonStartPause.setText("pause");
        ButtonReset.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer(){
        CountDownTimer.cancel();
        TimerRunning = false;
        ButtonStartPause.setText("Resume");
        ButtonReset.setVisibility(View.VISIBLE);
    }

    private void resetTimer(){
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        ButtonStartPause.setText("Start");
        ButtonReset.setVisibility(View.INVISIBLE);
        ButtonStartPause.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText(){
        int minutes = (int) mTimeLeftInMillis / 1000 / 60; // dang tinh bang long thanh int
        int seconds = (int) mTimeLeftInMillis / 1000 % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        TextViewcountDown.setText(timeLeftFormatted);
    }

}