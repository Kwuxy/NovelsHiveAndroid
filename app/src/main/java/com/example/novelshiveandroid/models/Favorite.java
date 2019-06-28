package com.example.novelshiveandroid.models;

public class Favorite {

    private Integer id;
    private int userId;
    private int storyId;

    public Favorite(int userId, int storyId) {
        this.userId = userId;
        this.storyId = storyId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStoryId() {
        return storyId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }
}
