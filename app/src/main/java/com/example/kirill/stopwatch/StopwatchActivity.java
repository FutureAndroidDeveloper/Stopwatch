package com.example.kirill.stopwatch;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class StopwatchActivity extends Activity {

    private int seconds = 0;
    private boolean running;
    private boolean blinking;
    private boolean wasBlinked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);

        // Restore activity instance
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            blinking = savedInstanceState.getBoolean("blinking");
            wasBlinked = savedInstanceState.getBoolean("wasBlinking");
        }

        runTimer();
        runBlink();
    }

    // Save instance of activity
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("blinking", blinking);
        savedInstanceState.putBoolean("wasBlinked", wasBlinked);
    }

    private void runTimer() {
        final TextView timeView = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hour = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;

                String timeText = String.format(Locale.UK, "%d : %02d : %02d", hour, minutes, secs);

                timeView.setText(timeText);

                if (running) {
                    seconds++;
                }

                handler.postDelayed(this, 1000);
            }
        });
    }

    private void runBlink() {
        final Button buttonView = (Button) findViewById(R.id.blink);
        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                if (blinking && !wasBlinked) {
                    buttonView.setBackgroundColor(Color.RED);
                    wasBlinked = true;
                } else if (blinking && wasBlinked) {
                    buttonView.setBackgroundColor(Color.BLUE);
                    wasBlinked = false;
                }

                handler.postDelayed(this, 1000);
            }
        });
    }

    public void onClickBlink(View view) {
        Button buttonView = (Button) findViewById(R.id.blink);

        if (blinking) {
            blinking = false;
        } else {
            blinking = true;
        }
    }

    // Start the stopwatch running when the Start button is clicked.
    public void onClickStart(View view) {
        running = true;
    }

    // Stop the stopwatch running when the Stop button is clicked.
    public void onClickStop(View view) {
        running = false;
    }

}
