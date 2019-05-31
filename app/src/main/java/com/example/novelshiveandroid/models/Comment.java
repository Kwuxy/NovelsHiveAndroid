package com.example.novelshiveandroid.models;

import com.google.gson.annotations.SerializedName;

public class Comment {

    private Integer id;

    @SerializedName("body")
    private String text;

    private int userId;
    private int storyChapterId;

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getUserId() {
        return userId;
    }

    public int getStoryChapterId() {
        return storyChapterId;
    }
}
