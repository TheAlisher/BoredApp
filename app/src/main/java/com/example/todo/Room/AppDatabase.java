package com.example.todo.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.todo.UI.models.Bored;

@Database(entities = {Bored.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BoredDao boredDao();
}
