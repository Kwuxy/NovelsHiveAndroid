package com.example.novelshiveandroid.presenters;

public interface StoryPresenter {
    void getStoryInfos(int storyId);
    void getStoryKind(int storyId);
    void getStoryRating(int storyId);
    void getStoryTags(int storyId);
    void getStoryChapters(int storyId);
    void getStoryChapterComments(int chapterId);
}
