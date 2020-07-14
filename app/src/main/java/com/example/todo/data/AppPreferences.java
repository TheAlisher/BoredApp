package com.example.todo.data;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {

    // ON BOARDING
    private static final String PREF_IS_FIRST_LAUNCH = "is_first_launch";
    // LIGHT / DARK | MODE
    private static final String PREF_LIGHT_DARK_MODE = "light_dark_mode";
    // LiveData / SwipeDelete MODE
    public static final String PREF_LIVE_DATA_SWIPE_DELETE_MODE = "liveData_swipeDelete";

    private SharedPreferences preferences;

    public AppPreferences(Context context) {
        preferences = context.getSharedPreferences(
                "bored_app_preferences",
                Context.MODE_PRIVATE);
    }

    // ON BOARDING
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

    // LiveData / SwipeDelete | MODE
    public boolean isLiveDataModeON() {
        return preferences.getBoolean(PREF_LIVE_DATA_SWIPE_DELETE_MODE, true);
    }

    public void setLiveDataMode(boolean flag) {
        preferences.edit().putBoolean(PREF_LIVE_DATA_SWIPE_DELETE_MODE, flag).apply();
    }
}
