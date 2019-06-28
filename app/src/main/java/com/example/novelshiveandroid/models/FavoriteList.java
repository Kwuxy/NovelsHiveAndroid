package com.example.novelshiveandroid.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FavoriteList {
    @SerializedName("stories")
    private List<Story> favoriteStories;

    public FavoriteList(List<Story> favoriteStories) {
        this.favoriteStories = favoriteStories;
    }

    public List<Story> getFavoriteStories() {
        return favoriteStories;
    }
}
