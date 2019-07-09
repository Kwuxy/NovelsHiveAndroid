package com.example.novelshiveandroid.models;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

import java.util.Date;

public class Chapter {

    private int id;
    private String title;

    @SerializedName("text")
    private LinkedTreeMap text;

    private Date update_date;
    private Boolean online;
    private int number;
    private int storyId;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LinkedTreeMap getText() {
        return text;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public Boolean getOnline() {
        return online;
    }

    public int getNumber() {
        return number;
    }

    public int getStoryId() {
        return storyId;
    }
}
