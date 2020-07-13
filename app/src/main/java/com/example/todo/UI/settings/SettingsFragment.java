package com.example.todo.UI.settings;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.todo.App;
import com.example.todo.R;

public class SettingsFragment extends Fragment {

    private Button buttonDayNightMode;
    private Button buttonLiveDataSwipeDelete;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializationViews(view);
        checkLiveDataSwipeDelete();
        buttonDayNightMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageDayNightModeClick();
            }
        });
        buttonLiveDataSwipeDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LiveDataSwipeDeleteClick();
            }
        });
    }

    private void initializationViews(View view) {
        buttonDayNightMode = view.findViewById(R.id.button_settings_day_night_mode);
        buttonLiveDataSwipeDelete = view.findViewById(R.id.button_settings_LiveData_or_SwipeDelete);
    }

    private void imageDayNightModeClick() {
        if (App.appPreferences.isDarkModeON()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            App.appPreferences.setModeDark(false);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            App.appPreferences.setModeDark(true);
        }
    }

    private void checkLiveDataSwipeDelete() {
        if (App.appPreferences.isLiveDataModeON()) {
            buttonLiveDataSwipeDelete.setText("LiveData");
        } else {
            buttonLiveDataSwipeDelete.setText("SwipeData");
        }
    }

    private void LiveDataSwipeDeleteClick() {
        if (App.appPreferences.isLiveDataModeON()) {
            buttonLiveDataSwipeDelete.setText("SwipeData");
            App.appPreferences.setLiveDataMode(false);
        } else {
            buttonLiveDataSwipeDelete.setText("LiveData");
            App.appPreferences.setLiveDataMode(true);
        }
    }
}
