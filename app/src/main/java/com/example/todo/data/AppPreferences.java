package com.example.todo.data;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {

    // IS FIRST LAUNCH
    private static final String PREF_IS_FIRST_LAUNCH = "is_first_launch";
    // LIGHT / DARK | MODE
    private static final String PREF_LIGHT_DARK_MODE = "light_dark_mode";

    private SharedPreferences preferences;

    public AppPreferences(Context context) {
        preferences = context.getSharedPreferences(
                "bored_app_preferences",
                Context.MODE_PRIVATE);
    }

    // IS FIRST LAUNCH
    public void setIsFirstLaunch() {
        preferences.edit().putBoolean(PREF_IS_FIRST_LAUNCH, false).apply();
    }

    public boolean isFirstLaunch() {
        return preferences.getBoolean(PREF_IS_FIRST_LAUNCH, true);
    }

    // LIGHT / DARK | MODE
    public boolean isDarkModeON() {
        return preferences.getBoolean(PREF_LIGHT_DARK_MODE, false);
    }

    public void setModeDark(boolean flag) {
        preferences.edit().putBoolean(PREF_LIGHT_DARK_MODE, flag).apply();
    }
}
