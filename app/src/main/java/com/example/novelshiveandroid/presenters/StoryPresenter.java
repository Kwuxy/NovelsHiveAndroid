package com.example.novelshiveandroid.presenters;

public interface StoryPresenter {
    void getStoryInfos(int storyId);
    void getStoryKind(int storyId);
    void getStoryRating(int storyId);
    void getStoryTags(int storyId);
    void getStoryChapters(int storyId);
    void getStoryChaptersCount(int storyId);

    void addToFavorites(int userId, int storyId);

    void checkIfStoryInUserFavorites(int userId, int storyId);
}
