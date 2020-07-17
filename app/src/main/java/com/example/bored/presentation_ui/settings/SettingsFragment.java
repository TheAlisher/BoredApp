package com.example.bored.presentation_ui.settings;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.bored.App;
import com.example.bored.R;
import com.example.bored.presentation_ui.onboard.IntroActivity;
import com.google.android.material.snackbar.Snackbar;

public class SettingsFragment extends Fragment {

    private ConstraintLayout fragmentSettings;
    private ImageView imageDayNightMode;
    private Button buttonTypeOfDeletionChoose;
    private Button buttonStartIntro;

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
        checkTypeOfDeletion();
        imageDayNightMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageDayNightModeClick();
            }
        });
        buttonTypeOfDeletionChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeOfDeletionClick();
            }
        });
        buttonStartIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startIntroClick();
            }
        });
    }

    private void initializationViews(View view) {
        fragmentSettings = view.findViewById(R.id.fragmentSettings);
        imageDayNightMode = view.findViewById(R.id.image_settings_day_night_mode);
        buttonTypeOfDeletionChoose = view.findViewById(R.id.button_settings_type_of_deletion_choose);
        buttonStartIntro = view.findViewById(R.id.button_settings_start_intro);
    }

    private void checkTypeOfDeletion() {
        if (App.appPreferences.isLiveDataON()) {
            buttonTypeOfDeletionChoose.setText(R.string.text_settings_live_data);
        }
        if (App.appPreferences.isSwipeDeleteON()) {
            buttonTypeOfDeletionChoose.setText(R.string.text_settings_swipe_data);
        }
        if (App.appPreferences.isManualDeleteON()) {
            buttonTypeOfDeletionChoose.setText(R.string.text_settings_manual_data);
        }
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

    private void typeOfDeletionClick() {
        if (App.appPreferences.isManualDeleteON()) {
            buttonTypeOfDeletionChoose.setText(R.string.text_settings_live_data);
            App.appPreferences.setLiveData(true);
            App.appPreferences.setSwipeDelete(false);
            App.appPreferences.setManualDelete(false);
        } else if (App.appPreferences.isLiveDataON()) {
            buttonTypeOfDeletionChoose.setText(R.string.text_settings_swipe_data);
            App.appPreferences.setLiveData(false);
            App.appPreferences.setSwipeDelete(true);
            App.appPreferences.setManualDelete(false);
        } else if (App.appPreferences.isSwipeDeleteON()) {
            buttonTypeOfDeletionChoose.setText(R.string.text_settings_manual_data);
            App.appPreferences.setLiveData(false);
            App.appPreferences.setSwipeDelete(false);
            App.appPreferences.setManualDelete(true);
        }
    }

    private void startIntroClick() {
        App.appPreferences.setNewLaunch();
        Snackbar.make(fragmentSettings, "При след. запуске откроется Intro", Snackbar.LENGTH_LONG)
                .setAction("Открыть", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getContext(), IntroActivity.class));
                        requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        requireActivity().finish();
                        return;
                    }
                })
                .show();
    }
}
