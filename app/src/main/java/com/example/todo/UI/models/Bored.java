package com.example.todo.UI.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Bored implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String category;
    private String explore;
    private String price;
    private Integer participants;
    private Integer accessibility;
    private String link;

    public Bored() {
    }

    public Bored(String category, String explore, String price, Integer participants, Integer accessibility, String link) {
        this.category = category;
        this.explore = explore;
        this.price = price;
        this.participants = participants;
        this.accessibility = accessibility;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getExplore() {
        return explore;
    }

    public void setExplore(String explore) {
        this.explore = explore;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getParticipants() {
        return participants;
    }

    public void setParticipants(Integer participants) {
        this.participants = participants;
    }

    public Integer getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(Integer accessibility) {
        this.accessibility = accessibility;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
