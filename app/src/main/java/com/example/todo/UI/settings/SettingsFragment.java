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

        buttonDayNightMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageDayNightModeClick();
            }
        });
    }

    private void initializationViews(View view) {
        buttonDayNightMode = view.findViewById(R.id.button_settings_day_night_mode);
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
}