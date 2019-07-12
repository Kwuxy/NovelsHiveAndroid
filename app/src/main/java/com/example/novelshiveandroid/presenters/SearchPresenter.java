package com.example.novelshiveandroid.presenters;

import java.util.Map;

public interface SearchPresenter {
    void searchStories(Map<String, Object> parameters);

    void getTags();
    void getKinds();
    void getRatings();
    void getUniverses();
    void getLanguages();
    void getStatus();
}
