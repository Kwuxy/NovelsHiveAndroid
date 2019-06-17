package com.example.novelshiveandroid.views;

import com.example.novelshiveandroid.models.Chapter;
import com.example.novelshiveandroid.models.Comment;
import com.example.novelshiveandroid.models.Kind;
import com.example.novelshiveandroid.models.Rating;
import com.example.novelshiveandroid.models.Story;
import com.example.novelshiveandroid.models.Tag;

import java.util.List;

public interface StoryView {
    void displayStoryInfos(Story story);
    void displayStoryKind(Kind kind);
    void displayStoryRating(Rating rating);
    void displayStoryTags(List<Tag> tags);
    void displayStoryChapters(List<Chapter> chapters);
    void displayStoryChaptersComments(List<Comment> comments);
}
