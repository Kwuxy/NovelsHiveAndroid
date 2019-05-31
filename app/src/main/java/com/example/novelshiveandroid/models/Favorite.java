package com.example.novelshiveandroid.models;

public class Favorite {

    private Integer id;
    private int userId;
    private int storyId;

    public Favorite(int userId, int storyId) {
        this.userId = userId;
        this.storyId = storyId;
    }
}
