package com.example.novelshiveandroid;

import com.google.gson.annotations.SerializedName;

public class Comment {

    private int id;

    @SerializedName("body")
    private String text;

    private int userId;
    private int storyChapterId;

}
