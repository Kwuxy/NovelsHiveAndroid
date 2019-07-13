package com.example.novelshiveandroid.presenters;

public interface ReaderPresenter {
    void getReadingChapterInfos(int chapterId, int userId);

    void addToFavorites(int userId, int storyId);
    void removeToFavorites(int favoriteId);

}
