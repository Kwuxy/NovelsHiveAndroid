package com.example.novelshiveandroid.views;

import com.example.novelshiveandroid.models.ReadingChapter;

public interface ReaderView {
    void displayReadingChapter(ReadingChapter readingChapter);
    void goToPreviousChapter(Double previousChapterId);
    void goToNextChapter(Double nextChapterId);
}
