package com.example.novelshiveandroid.presenters;

public interface StoryPresenter {
    void getStoryInfos(int storyId);
    void getStoryKind(int storyId);
    void getStoryRating(int storyId);
    void getStoryTags(int storyId);
    void getStoryImage(String filePath);
    void getStoryChapters(int storyId);
    void getStoryChaptersCount(int storyId);

    void addToFavorites(int userId, int storyId);
    void removeToFavorites(int favoriteId);

    void checkIfStoryInUserFavorites(int userId, int storyId);
}
