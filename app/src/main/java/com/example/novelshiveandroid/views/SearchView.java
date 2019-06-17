package com.example.novelshiveandroid.views;

import com.example.novelshiveandroid.models.Kind;
import com.example.novelshiveandroid.models.Language;
import com.example.novelshiveandroid.models.Rating;
import com.example.novelshiveandroid.models.Status;
import com.example.novelshiveandroid.models.Tag;
import com.example.novelshiveandroid.models.Universe;

import java.util.List;

public interface SearchView {
    void displayTags(List<Tag> tags);
    void displayKinds(List<Kind> kinds);
    void displayRatings(List<Rating> ratings);
    void displayUniverses(List<Universe> universes);
    void displayLanguages(List<Language> languages);
    void displayStatus(List<Status> status);
}
