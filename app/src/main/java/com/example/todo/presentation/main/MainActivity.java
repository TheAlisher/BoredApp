package com.example.todo.presentation.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.todo.App;
import com.example.todo.R;
import com.example.todo.presentation.intro.IntroActivity;
import com.example.todo.ui.favorites.FavoritesFragment;
import com.example.todo.ui.home.HomeFragment;
import com.example.todo.ui.settings.SettingsFragment;
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

        createBottomNavigationMenu();
    }

    private void createBottomNavigationMenu() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        openFragmentHome();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        openFragmentHome();
                        return true;

                    case R.id.navigation_favorites:
                        openFragmentFavorites();
                        return true;

                    case R.id.navigation_settings:
                        openFragmentSettings();
                        return true;
                }
                return false;
            }
        });
    }

    private void openFragmentHome() {
        HomeFragment fragmentHome = new HomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace
                        (R.id.frameLayout_main_container, fragmentHome)
                .commit();
    }

    private void openFragmentFavorites() {
        FavoritesFragment fragmentFavorites = new FavoritesFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace
                        (R.id.frameLayout_main_container, fragmentFavorites)
                .commit();
    }

    private void openFragmentSettings() {
        SettingsFragment fragmentSettings = new SettingsFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace
                        (R.id.frameLayout_main_container, fragmentSettings)
                .commit();
    }
}
