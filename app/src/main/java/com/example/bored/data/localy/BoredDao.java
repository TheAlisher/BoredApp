package com.example.bored.data.localy;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.bored.model.BoredAction;

import java.util.List;

@Dao
public interface BoredDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(BoredAction boredAction);

    @Delete
    void delete(BoredAction boredAction);

    @Query("SELECT * FROM bored_action WHERE uuid =:key")
    BoredAction get(String key);

    @Query("SELECT * FROM bored_action")
    List<BoredAction> getAll();

    @Query("SELECT * FROM bored_action")
    LiveData<List<BoredAction>> getAllLive();
}
