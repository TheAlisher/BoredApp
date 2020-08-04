package com.example.bored.presentation_ui.settings;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.bored.App;
import com.example.bored.R;

public class SettingsFragment extends Fragment {

    private ImageView imageDayNightMode;
    private Button buttonTypeOfLoading;
    private Button buttonTypeOfDeletion;

    public SettingsFragment() {
    }

    public static Fragment newInstance() {
        return new SettingsFragment();
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
        checkTypeOfLoadingAndDeletion();
        imageDayNightMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsDayNightModeClick();
            }
        });
        buttonTypeOfLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsTypeOfLoadingClick();
            }
        });
        buttonTypeOfDeletion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsTypeOfDeletionClick();
            }
        });
    }

    private void initializationViews(View view) {
        imageDayNightMode = view.findViewById(R.id.imageSwitch_settings_day_night_mode);
        buttonTypeOfLoading = view.findViewById(R.id.button_settings_type_of_loading);
        buttonTypeOfDeletion = view.findViewById(R.id.button_settings_type_of_deletion);
    }

    private void checkTypeOfLoadingAndDeletion() {
        if (App.appPreferences.isManualData()) {
            buttonTypeOfLoading.setText(R.string.text_settings_manual_data);
        }
        if (App.appPreferences.isLiveData()) {
            buttonTypeOfLoading.setText(R.string.text_settings_live_data);
        }
        if (App.appPreferences.isClickDelete()) {
            buttonTypeOfDeletion.setText(R.string.text_settings_click_delete);
        }
        if (App.appPreferences.isSwipeDelete()) {
            buttonTypeOfDeletion.setText(R.string.text_settings_swipe_delete);
        }
    }

    private void settingsDayNightModeClick() {
        if (App.appPreferences.isDarkModeON()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            App.appPreferences.setModeDark(false);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            App.appPreferences.setModeDark(true);
        }
    }

    private void settingsTypeOfLoadingClick() {
        if (App.appPreferences.isManualData()) {
            buttonTypeOfLoading.setText(R.string.text_settings_live_data);
            App.appPreferences.setManualData(false);
            App.appPreferences.setLiveData(true);
        } else if (App.appPreferences.isLiveData()) {
            buttonTypeOfLoading.setText(R.string.text_settings_manual_data);
            App.appPreferences.setManualData(true);
            App.appPreferences.setLiveData(false);
        }
    }

    private void settingsTypeOfDeletionClick() {
        if (App.appPreferences.isClickDelete()) {
            buttonTypeOfDeletion.setText(R.string.text_settings_swipe_delete);
            App.appPreferences.setClickDelete(false);
            App.appPreferences.setSwipeDelete(true);
        } else if (App.appPreferences.isSwipeDelete()) {
            buttonTypeOfDeletion.setText(R.string.text_settings_click_delete);
            App.appPreferences.setClickDelete(true);
            App.appPreferences.setSwipeDelete(false);
        }
    }
}
