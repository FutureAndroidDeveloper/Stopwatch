package com.example.kirill.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class StopwatchActivity extends Activity {

    private int seconds = 0;
    private boolean running = ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        runTimer();
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

    // Start the stopwatch running when the Start button is clicked.
    public void onClickStart(View view) {
        running = true;
    }

    // Stop the stopwatch running when the Stop button is clicked.
    public void onClickStop(View view) {
        running = false;
    }

    // Reset the stopwatch running when the Reset button is clicked.
    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }
}
