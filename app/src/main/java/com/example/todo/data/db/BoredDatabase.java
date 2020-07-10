package com.example.todo.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.todo.data.localy.BoredDao;
import com.example.todo.model.BoredAction;

@Database(
        entities = {BoredAction.class},
        version = BoredDatabase.VERSION,
        exportSchema = false)
public abstract class BoredDatabase extends RoomDatabase {

    public static final int VERSION = 1;

    public abstract BoredDao boredDao();
}
