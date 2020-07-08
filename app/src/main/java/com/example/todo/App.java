package com.example.todo;

import android.app.Application;

import androidx.room.Room;

import com.example.todo.Room.AppDatabase;
import com.example.todo.data.AppPreferences;
import com.example.todo.data.BoredAPIClient;

public class App extends Application {

    //AppPreferences
    public static AppPreferences appPreferences;
    //API
    public static BoredAPIClient boredAPIClient;
    //Room Database
    private AppDatabase database;
    public static App instance;

    @Override
    public void onCreate() {
        super.onCreate();

        appPreferences = new AppPreferences(this);
        boredAPIClient = new BoredAPIClient();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .allowMainThreadQueries()
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
