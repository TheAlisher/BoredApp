package com.example.bored;

import android.app.Application;

import androidx.room.Room;

import com.example.bored.data.AppPreferences;
import com.example.bored.data.BoredRepository;
import com.example.bored.data.remote.BoredAPIClient;
import com.example.bored.data.db.BoredDatabase;
import com.example.bored.data.local.BoredStorage;

public class App extends Application {

    //AppPreferences
    public static AppPreferences appPreferences;
    //Room Database
    private static BoredDatabase boredDatabase;
    //Repository
    public static BoredRepository boredRepository;

    @Override
    public void onCreate() {
        super.onCreate();

        appPreferences = new AppPreferences(this);
        BoredAPIClient boredAPIClient = new BoredAPIClient();
        boredDatabase = Room.databaseBuilder(
                this,
                BoredDatabase.class,
                "bored.db"
        ).fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        BoredStorage boredStorage = new BoredStorage(boredDatabase.boredDao());
        boredRepository = new BoredRepository(boredStorage, boredAPIClient);
    }
}
