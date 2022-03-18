package com.tz.timernew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void btnDenyAction(View view) {
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void btnApplyAction(View view) {
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}