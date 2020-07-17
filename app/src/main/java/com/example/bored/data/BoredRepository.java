package com.example.bored.data;

import androidx.lifecycle.LiveData;

import com.example.bored.data.local.IBoredStorage;
import com.example.bored.data.remote.BoredAPIClient;
import com.example.bored.model.BoredAction;

import java.util.List;

public class BoredRepository {

    public BoredAction lastAction = null;
    private IBoredStorage iBoredStorage;
    private BoredAPIClient boredAPIClient;

    public BoredRepository(IBoredStorage boredStorage, BoredAPIClient boredAPIClient) {
        this.iBoredStorage = boredStorage;
        this.boredAPIClient = boredAPIClient;
    }

    public void saveBoredAction(BoredAction boredAction) {
        iBoredStorage.saveBoredAction(boredAction);
    }

    public void deleteBoredAction(BoredAction boredAction) {
        iBoredStorage.deleteBoredAction(boredAction);
    }

    public BoredAction getBoredAction(String key) {
        return iBoredStorage.getBoredAction(key);
    }

    public List<BoredAction> getAllActions() {
        return iBoredStorage.getAllActions();
    }

    public LiveData<List<BoredAction>> getAllActionsLive() {
        return iBoredStorage.getAllActionsLive();
    }

    public void getAction(
            String type,
            Float minPrice,
            Float maxPrice,
            Float minAccessibility,
            Float maxAccessibility,
            BoredAPIClient.BoredActionCallback callback
    ) {
        boredAPIClient.getAction(type,
                minPrice,
                maxPrice,
                minAccessibility,
                maxAccessibility,
                new BoredAPIClient.BoredActionCallback() {
            @Override
            public void onSuccess(BoredAction result) {
                lastAction = result;
                callback.onSuccess(result);
            }

            @Override
            public void onFailure(Exception exception) {
                callback.onFailure(exception);
            }
        });
    }
}
