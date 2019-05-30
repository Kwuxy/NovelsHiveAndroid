package com.example.novelshiveandroid.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Chapter {

    private int id;
    private String title;

    @SerializedName("body")
    private String text;

    private Date update_date;
    private Boolean online;
    private int number;
    private int storyId;
}
