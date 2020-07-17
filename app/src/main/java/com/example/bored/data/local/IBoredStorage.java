package com.example.bored.data.local;

import androidx.lifecycle.LiveData;

import com.example.bored.model.BoredAction;

import java.util.List;

public interface IBoredStorage {

    void saveBoredAction(BoredAction boredAction);

    void deleteBoredAction(BoredAction boredAction);

    BoredAction getBoredAction(String key);

    List<BoredAction> getAllActions();

    LiveData<List<BoredAction>> getAllActionsLive();
}
