package com.example.novelshiveandroid.presenters;

import java.util.ArrayList;

public interface StoryPresenter {
    void getStoryInfos(int storyId);
    String convertSynopsis(ArrayList<Double> doubleData);
    void getStoryKind(int storyId);
    void getStoryRating(int storyId);
    void getStoryTags(int storyId);
    void getStoryChapters(int storyId);
    void getStoryChaptersCount(int storyId);
    void getStoryChapterComments(int chapterId);

    void addToFavorites(int userId, int storyId);
}
