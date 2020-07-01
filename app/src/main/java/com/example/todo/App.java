package com.example.todo;

import android.app.Application;

import com.example.todo.data.AppPreferences;
import com.example.todo.data.BoredAPIClient;

public class App extends Application {

    public static AppPreferences appPreferences;
    public static BoredAPIClient boredAPIClient;

    @Override
    public void onCreate() {
        super.onCreate();

        appPreferences = new AppPreferences(this);
        boredAPIClient = new BoredAPIClient();
    }
}
