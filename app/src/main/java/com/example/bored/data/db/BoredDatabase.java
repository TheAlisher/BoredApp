package com.example.bored.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.bored.data.localy.BoredDao;
import com.example.bored.model.BoredAction;

@Database(
        entities = {BoredAction.class},
        version = BoredDatabase.VERSION,
        exportSchema = false)
public abstract class BoredDatabase extends RoomDatabase {

    public static final int VERSION = 1;

    public abstract BoredDao boredDao();
}
