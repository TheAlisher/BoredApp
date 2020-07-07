package com.example.todo.presentation.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.todo.App;
import com.example.todo.R;
import com.example.todo.presentation.intro.IntroActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (App.appPreferences.isFirstLaunch()) {
            startActivity(new Intent(this, IntroActivity.class));
        }

        createNavigationBottom();
        isNightModeOn();
    }

    private void createNavigationBottom() {
        BottomNavigationView navView = findViewById(R.id.navigationView);
        NavController navController = Navigation.findNavController(this, R.id.navigation_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
    }

    private void isNightModeOn() {
        if (App.appPreferences.isDarkModeON()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
