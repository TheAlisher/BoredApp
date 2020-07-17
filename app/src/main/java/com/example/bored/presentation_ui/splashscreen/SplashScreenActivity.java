package com.example.bored.presentation_ui.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bored.presentation_ui.main.MainActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.start(this);
        finish();
    }
}