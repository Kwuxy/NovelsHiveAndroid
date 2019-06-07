package com.example.novelshiveandroid.controllers;

import com.example.novelshiveandroid.models.Chapter;
import com.example.novelshiveandroid.models.Comment;
import com.example.novelshiveandroid.models.Kind;
import com.example.novelshiveandroid.models.Rating;
import com.example.novelshiveandroid.models.Story;
import com.example.novelshiveandroid.models.StoryHasStoryTag;
import com.example.novelshiveandroid.models.Tag;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.novelshiveandroid.controllers.ControllerConfig.jsonPlaceHolderApi;

public abstract class StoryInfosController {

    //Get Story Infos To Display Presentation Page
    public static Story getStoryInfos(int storyId){
        final List<Story> onlyOneStory = new ArrayList<>();
        Call<Story> call = jsonPlaceHolderApi.getStoryInfos(storyId);
        call.enqueue(new Callback<Story>() {
            @Override
            public void onResponse(Call<Story> call, Response<Story> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }

                onlyOneStory.clear();
                onlyOneStory.add(response.body());
            }

            @Override
            public void onFailure(Call<Story> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });

        return onlyOneStory.get(0);

    }

    //Get Story Kind
    public static Kind getStoryKind(int storyId){
        final List<Kind> onlyOneKind = new ArrayList<>();
        Call<Kind> call = jsonPlaceHolderApi.getStoryKind(storyId);
        call.enqueue(new Callback<Kind>() {
            @Override
            public void onResponse(Call<Kind> call, Response<Kind> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }

                onlyOneKind.clear();
                onlyOneKind.add(response.body());
            }

            @Override
            public void onFailure(Call<Kind> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });

        return onlyOneKind.get(0);

    }

    //Get Story Rating
    public static Rating getStoryRating(int storyId){
        final List<Rating> onlyOneRating = new ArrayList<>();
        Call<Rating> call = jsonPlaceHolderApi.getStoryRating(storyId);
        call.enqueue(new Callback<Rating>() {
            @Override
            public void onResponse(Call<Rating> call, Response<Rating> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }

                onlyOneRating.clear();
                onlyOneRating.add(response.body());
            }

            @Override
            public void onFailure(Call<Rating> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });

        return onlyOneRating.get(0);

    }
    
    //Get Story Tags
    public static List<Tag> getStoryTags(int storyId){
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

        final List<Tag> storyTags = new ArrayList<>();
        Call<Tag> call2;
        for(StoryHasStoryTag link : storyTagsLinks){
            call2 = jsonPlaceHolderApi.getStoryTag(link.getId());
            call2.enqueue(new Callback<Tag>() {
                @Override
                public void onResponse(Call<Tag> call2, Response<Tag> response) {
                    if (!response.isSuccessful()) {
                        System.out.print("Code : " + response.code());
                        return;
                    }
                    storyTags.add(response.body());
                }

                @Override
                public void onFailure(Call<Tag> call2, Throwable t) {
                    System.out.print(t.getMessage());
                }
            });
        }

        return storyTags;

    }

    //Get Story Chapters Count
    public static Integer getStoryChaptersCount(int storyId){
        final List<Integer> onlyOneCount = new ArrayList<>();
        Call<Integer> call = jsonPlaceHolderApi.getStoryChaptersCount(storyId);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }

                onlyOneCount.clear();
                onlyOneCount.add(response.body());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });

        return onlyOneCount.get(0);

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

    //Get Chapter Infos To Display It
    public static Chapter getChapterInfos(int chapterId){
        final List<Chapter> onlyOneChapter = new ArrayList<>();
        Call<Chapter> call = jsonPlaceHolderApi.getChapterInfos(chapterId);
        call.enqueue(new Callback<Chapter>() {
            @Override
            public void onResponse(Call<Chapter> call, Response<Chapter> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }

                onlyOneChapter.clear();
                onlyOneChapter.add(response.body());
            }

            @Override
            public void onFailure(Call<Chapter> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });

        return onlyOneChapter.get(0);

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
