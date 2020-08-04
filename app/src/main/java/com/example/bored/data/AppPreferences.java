package com.example.bored.data;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {

    // ONBOARD
    private static final String PREF_IS_FIRST_LAUNCH = "is_first_launch";
    // LIGHT / DARK | MODE
    private static final String PREF_LIGHT_DARK_MODE = "light_dark_mode";
    // MANUALDATA / LIVEDATA | LOAD MODE
    public static final String PREF_MANUAL_DATA = "manual_data";
    public static final String PREF_LIVE_DATA = "live_data";
    // CLICK / SWIPE | DELETE MODE
    public static final String PREF_CLICK_DELETE = "click_delete";
    public static final String PREF_SWIPE_DELETE = "swipe_delete";

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

    // MANUALDATA / LIVEDATA | LOAD MODE
    public boolean isManualData() {
        return preferences.getBoolean(PREF_MANUAL_DATA, true);
    }

    public void setManualData(boolean flag) {
        preferences.edit().putBoolean(PREF_MANUAL_DATA, flag).apply();
    }

    public boolean isLiveData() {
        return preferences.getBoolean(PREF_LIVE_DATA, false);
    }

    public void setLiveData(boolean flag) {
        preferences.edit().putBoolean(PREF_LIVE_DATA, flag).apply();
    }

    // CLICK / SWIPE | DELETE MODE
    public boolean isClickDelete() {
        return preferences.getBoolean(PREF_CLICK_DELETE, true);
    }

    public void setClickDelete(boolean flag) {
        preferences.edit().putBoolean(PREF_CLICK_DELETE, flag).apply();
    }

    public boolean isSwipeDelete() {
        return preferences.getBoolean(PREF_SWIPE_DELETE, false);
    }

    public void setSwipeDelete(boolean flag) {
        preferences.edit().putBoolean(PREF_SWIPE_DELETE, flag).apply();
    }
}
