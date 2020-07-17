package com.example.bored.data;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {

    // ON BOARD
    private static final String PREF_IS_FIRST_LAUNCH = "is_first_launch";
    // LIGHT / DARK | MODE
    private static final String PREF_LIGHT_DARK_MODE = "light_dark_mode";
    // LiveData / SwipeDelete / ManualDeleteInCode | MODE
    public static final String PREF_LIVE_DATA = "live_data";
    public static final String PREF_SWIPE_DELETE = "swipe_delete";
    public static final String PREF_MANUAL_DELETE = "manual_delete";

    private SharedPreferences preferences;

    public AppPreferences(Context context) {
        preferences = context.getSharedPreferences(
                "bored_app_preferences",
                Context.MODE_PRIVATE);
    }

    // ON BOARD
    public void setLaunched() {
        preferences.edit().putBoolean(PREF_IS_FIRST_LAUNCH, false).apply();
    }

    public void setNewLaunch() {
        preferences.edit().putBoolean(PREF_IS_FIRST_LAUNCH, true).apply();
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

    // LiveData / SwipeDelete / ManualDeleteInCode | MODE
    public boolean isLiveDataON() {
        return preferences.getBoolean(PREF_LIVE_DATA, true);
    }

    public void setLiveData(boolean flag) {
        preferences.edit().putBoolean(PREF_LIVE_DATA, flag).apply();
    }

    public boolean isSwipeDeleteON() {
        return preferences.getBoolean(PREF_SWIPE_DELETE, true);
    }

    public void setSwipeDelete(boolean flag) {
        preferences.edit().putBoolean(PREF_SWIPE_DELETE, flag).apply();
    }

    public boolean isManualDeleteON() {
        return preferences.getBoolean(PREF_MANUAL_DELETE, true);
    }

    public void setManualDelete(boolean flag) {
        preferences.edit().putBoolean(PREF_MANUAL_DELETE, flag).apply();
    }
}
