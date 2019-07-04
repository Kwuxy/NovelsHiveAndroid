package com.example.novelshiveandroid.models;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

public class ReadingChapter {

    @SerializedName("chapter")
    private LinkedTreeMap chapter;

    public LinkedTreeMap getChapter() {
        return chapter;
    }

    public String getTitle() {
        return chapter.get("title").toString();
    }

    public LinkedTreeMap getText() {
        return (LinkedTreeMap)chapter.get("text");
    }

    public String getStoryTitle() {
        return chapter.get("storyTitle").toString();
    }

    public Double getPreviousChapter() {
        return (Double)((LinkedTreeMap)chapter.get("previousChapter")).get("id");
    }

    public Double getNextChapter() {
        return (Double)((LinkedTreeMap)chapter.get("nextChapter")).get("id");
    }
}
