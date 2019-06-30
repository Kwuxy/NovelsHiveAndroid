package com.example.novelshiveandroid.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TagList {
    @SerializedName("tags")
    private List<Tag> storyTags;

    public TagList(List<Tag> storyTags) {
        this.storyTags = storyTags;
    }

    public List<Tag> getStoryTags() {
        return storyTags;
    }
}
