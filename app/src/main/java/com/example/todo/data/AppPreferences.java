package com.example.todo.data;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {

    private static final String PREF_IS_FIRST_LAUNCH = "is_first_launch";
    private SharedPreferences preferences;

    public AppPreferences(Context context) {
        preferences = context.getSharedPreferences(
                "bored_app_preferences",
                Context.MODE_PRIVATE);
    }

    public void setIsFirstLaunch() {
        preferences.edit().putBoolean(PREF_IS_FIRST_LAUNCH, false).apply();
    }

    public boolean isFirstLaunch() {
        return preferences.getBoolean(PREF_IS_FIRST_LAUNCH, true);
    }
}
