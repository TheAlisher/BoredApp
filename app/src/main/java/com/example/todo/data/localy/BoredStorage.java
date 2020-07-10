package com.example.todo.data.localy;

import com.example.todo.model.BoredAction;

import java.util.List;

public class BoredStorage  {

    private BoredDao dao;

    public BoredStorage(BoredDao dao) {
        this.dao = dao;
    }

    public void saveBoredAction(BoredAction boredAction) {
        dao.insert(boredAction);
    }

    public void deleteBoredAction(BoredAction boredAction) {
        dao.delete(boredAction);
    }

    public BoredAction getBoredAction(String key) {
        return dao.get(key);
    }

    public List<BoredAction> getAllActions() {
        return dao.getAll();
    }
}
