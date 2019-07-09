package com.example.novelshiveandroid.models;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

import java.util.Date;

public class Story {

    private int id;
    private Date publication_date;
    private Date update_date;
    private String title;

    @SerializedName("synopsis")
    private LinkedTreeMap synopsis;

    private String panel;
    private int userId;
    private int storyStatusId;
    private int languageId;
    private int storyKindId;
    private int storyRatingId;
    private int universeId;

    public int getId() {
        return id;
    }

    public Date getPublication_date() {
        return publication_date;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public String getTitle() {
        return title;
    }

    public LinkedTreeMap getSynopsis() {
        return synopsis;
    }

    public String getPanel() {
        return panel;
    }

    public int getUserId() {
        return userId;
    }

    public int getStoryStatusId() {
        return storyStatusId;
    }

    public int getLanguageId() {
        return languageId;
    }

    public int getStoryKindId() {
        return storyKindId;
    }

    public int getStoryRatingId() {
        return storyRatingId;
    }

    public int getUniverseId() {
        return universeId;
    }
}
