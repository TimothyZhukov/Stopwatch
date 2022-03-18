package com.tz.timernew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    Vibrator vibrator;
    private TextView textViewTimer;
    private int seconds = 0;
    private boolean isRunning = false;
    private boolean wasRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewTimer = findViewById(R.id.textViewTimer);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            isRunning = savedInstanceState.getBoolean("isRunning");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = isRunning;
        isRunning = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isRunning = wasRunning;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds", seconds);
        outState.putBoolean("isRunning", isRunning);
        outState.putBoolean("wasRunning", wasRunning);
    }

    public void btnStartAction(View view) {
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.start_click);
        onClickVibrator();
        mediaPlayer.start();
        isRunning = true;
    }

    public void btnPauseAction(View view) {
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.pause_click);
        onClickVibrator();
        mediaPlayer.start();
        isRunning = false;
    }

    public void btnResetAction(View view) {
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.reset_click);
        onClickVibrator();
        mediaPlayer.start();
        isRunning = false;
        seconds = 0;
    }

    public void onClickVibrator() {
        vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(10);
    }

    public void btnSettingsAction(View view) {
        onClickVibrator();
        intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    private void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                int hoursT = seconds / 3600;
                int minutesT = (seconds % 3600) / 60;
                int secondsT = seconds % 60;
                String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hoursT, minutesT, secondsT);
                textViewTimer.setText(time);

                if (isRunning) {
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });



    }
}