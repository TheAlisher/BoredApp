package com.example.bored;

import android.app.Application;

import androidx.room.Room;

import com.example.bored.data.AppPreferences;
import com.example.bored.data.remote.BoredAPIClient;
import com.example.bored.data.db.BoredDatabase;
import com.example.bored.data.local.BoredStorage;

public class App extends Application {

    //AppPreferences
    public static AppPreferences appPreferences;
    //API
    public static BoredAPIClient boredAPIClient;
    //Room Database
    private static BoredDatabase boredDatabase;
    public static BoredStorage boredStorage;

    @Override
    public void onCreate() {
        super.onCreate();

        appPreferences = new AppPreferences(this);
        boredAPIClient = new BoredAPIClient();
        boredDatabase = Room.databaseBuilder(
                this,
                BoredDatabase.class,
                "bored.db"
        ).fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        boredStorage = new BoredStorage(boredDatabase.boredDao());
    }
}
