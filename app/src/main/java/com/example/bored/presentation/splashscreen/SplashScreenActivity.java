package com.example.bored.presentation.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bored.presentation.UI.main.MainActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.start(this);
        finish();
    }
}