package com.example.todo.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.todo.UI.models.Bored;

import java.util.List;

@Dao
public interface BoredDao {

    @Query("SELECT * FROM bored")
    List<Bored> getAll();

    @Query("SELECT * FROM bored")
    LiveData<List<Bored>> getAllLive();

    @Insert
    void insert(Bored bored);

    @Delete
    void delete(Bored bored);
}
