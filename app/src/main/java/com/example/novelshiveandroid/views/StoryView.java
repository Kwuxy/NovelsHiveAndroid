package com.example.novelshiveandroid.views;

import android.graphics.Bitmap;

import com.example.novelshiveandroid.models.Chapter;
import com.example.novelshiveandroid.models.Comment;
import com.example.novelshiveandroid.models.Favorite;
import com.example.novelshiveandroid.models.Kind;
import com.example.novelshiveandroid.models.PublishedComment;
import com.example.novelshiveandroid.models.Rating;
import com.example.novelshiveandroid.models.Story;
import com.example.novelshiveandroid.models.Tag;

import java.util.List;

public interface StoryView {
    void displayStoryInfos(Story story);
    void displayStoryKind(Kind kind);
    void displayStoryRating(Rating rating);
    void displayStoryTags(List<Tag> tags);
    void displayStoryImage(Bitmap bmpImage);
    void displayStoryChapters(List<Chapter> chapters);
    void displayStoryChaptersCount(Integer count);

    void displayFavoriteAdding();
    void displayFavoriteDeleting();

    void getFavoriteId(Favorite favorite);
    void setInFavoriteValue(boolean checkingResult);
}
