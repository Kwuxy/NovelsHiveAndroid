package com.example.novelshiveandroid.controllers;

import com.example.novelshiveandroid.models.Chapter;
import com.example.novelshiveandroid.models.Comment;
import com.example.novelshiveandroid.models.StoryHasStoryTag;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.novelshiveandroid.controllers.ControllerConfig.jsonPlaceHolderApi;

public abstract class StoryInfosController {

    //Get Story Tags Links
    public static List<StoryHasStoryTag> getStoryHasStoryTags(int storyId){
        final List<StoryHasStoryTag> storyTagsLinks = new ArrayList<>();
        Call<List<StoryHasStoryTag>> call = jsonPlaceHolderApi.getStoryHasStoryTags(storyId);
        call.enqueue(new Callback<List<StoryHasStoryTag>>() {
            @Override
            public void onResponse(Call<List<StoryHasStoryTag>> call, Response<List<StoryHasStoryTag>> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }

                storyTagsLinks.clear();
                storyTagsLinks.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<StoryHasStoryTag>> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });

        return storyTagsLinks;

    }

    //Get Story Chapters List
    public static List<Chapter> getStoryChapters(int storyId){
        final List<Chapter> storyChapters = new ArrayList<>();
        Call<List<Chapter>> call = jsonPlaceHolderApi.getStoryChapters(storyId);
        call.enqueue(new Callback<List<Chapter>>() {
            @Override
            public void onResponse(Call<List<Chapter>> call, Response<List<Chapter>> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }

                storyChapters.clear();
                storyChapters.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Chapter>> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });

        return storyChapters;

    }

    //Get Chapter Published Comments
    public static List<Comment> getChapterComments(int chapterId){
        final List<Comment> chapterComments = new ArrayList<>();
        Call<List<Comment>> call = jsonPlaceHolderApi.getChapterComments(chapterId);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }

                chapterComments.clear();
                chapterComments.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });

        return chapterComments;

    }
}
