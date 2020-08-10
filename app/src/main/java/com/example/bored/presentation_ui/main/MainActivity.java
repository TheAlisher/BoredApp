package com.example.bored.presentation_ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.bored.App;
import com.example.bored.R;
import com.example.bored.presentation_ui.favorites.FavoritesFragment;
import com.example.bored.presentation_ui.settings.SettingsFragment;
import com.example.bored.presentation_ui.onboard.IntroActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    private ViewPager mViewPager;
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isDarkModeON();
        isFirstLaunch();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializationViews();
        createBottomNavigationWithViewPager();
    }

    private void isDarkModeON() {
        if (App.appPreferences.isDarkModeON()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void isFirstLaunch() {
        if (App.appPreferences.isFirstLaunch()) {
            startActivity(new Intent(this, IntroActivity.class));
            finish();
        }
    }

    private void initializationViews() {
        mViewPager = findViewById(R.id.viewPager_main);
        mBottomNavigationView = findViewById(R.id.navigationView_main);
    }

    private void createBottomNavigationWithViewPager() {
        mViewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        mViewPager.setOffscreenPageLimit(2);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_main:
                        mViewPager.setCurrentItem(0,false);
                        break;
                    case R.id.navigation_favorites:
                        mViewPager.setCurrentItem(1,false);
                        break;
                    case R.id.navigation_settings:
                        mViewPager.setCurrentItem(2,false);
                        break;
                }
                return true;
            }
        });
    }

    public class MainPagerAdapter extends FragmentPagerAdapter {

        public MainPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        public MainPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            switch (position) {
                case 0:
                    fragment = MainFragment.newInstance();
                    break;
                case 1:
                    fragment = FavoritesFragment.newInstance();
                    break;
                default:
                    fragment = SettingsFragment.newInstance();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    @Override
    public void onBackPressed() {
        if (mViewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mViewPager.setCurrentItem(0, false);
            mBottomNavigationView.setSelectedItemId(R.id.navigation_main);
        }
    }
}
