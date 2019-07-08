package com.example.novelshiveandroid.views;

import com.example.novelshiveandroid.models.Favorite;
import com.example.novelshiveandroid.models.ReadingChapter;

public interface ReaderView {
    void displayReadingChapter(ReadingChapter readingChapter);
    void goToPreviousChapter(Double previousChapterId);
    void goToNextChapter(Double nextChapterId);

    void displayFavoriteAdding();
    void displayFavoriteDeleting();

    void getFavoriteId(Favorite favorite);
    void setInFavoriteValue(boolean checkingResult);
}
