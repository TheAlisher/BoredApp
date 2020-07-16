package com.example.bored.data;

import com.example.bored.data.local.BoredStorage;
import com.example.bored.data.remote.BoredAPIClient;

public class BoredRepository {

    private BoredStorage boredStorage;
    private BoredAPIClient boredAPIClient;

    public BoredRepository(BoredStorage boredStorage, BoredAPIClient boredAPIClient) {
        this.boredStorage = boredStorage;
        this.boredAPIClient = boredAPIClient;
    }
}
