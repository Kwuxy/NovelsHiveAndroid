package com.example.novelshiveandroid.presenters;

import java.util.ArrayList;

public interface ReaderPresenter {
    void getChapterInfos(int chapterId);
    String convertText(ArrayList<Double> doubleData);
}
