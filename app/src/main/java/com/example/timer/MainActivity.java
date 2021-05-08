package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Button startButton, resetButton;
    TextView timerText;
    boolean isStarted = true;
    Timer timer;
    int timerCount = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.start);
        resetButton = findViewById(R.id.reset);
        timerText = findViewById(R.id.timer);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStarted) {
                    timer = new Timer();
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    int minutes = timerCount / 60;
                                    int second = timerCount % 60;
                                    String secString = "" + second;
                                    if (second < 10) {
                                        secString = "0" + second;
                                    }

                                    timerText.setText(minutes + ":" + secString);
                                    // Task 1: make timer 5 times faster
                                    //timerCount = timerCount + 5;

                                    //This for Task 2 and 3 -- countDown
                                    timerCount =  timerCount - 5;

                                    // Task 3: removal of start/reset button when time is up
                                    if (timerCount < 0) {
                                        // This setVisibility removes start/pause button
                                        startButton.setVisibility(View.GONE);
                                        timerText.setText("Time is up");
                                    }
                                }
                            });

                        }
                    }, 0, 1000);
                    startButton.setText("PAUSE");
                }
                else {
                    timer.cancel();
                    startButton.setText("START");
                }
                isStarted = !isStarted;
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerCount = 60;
                timer.cancel();
                isStarted = true;
                timerText.setText("0:00");
                startButton.setText("START");
            }
        });
    }

}