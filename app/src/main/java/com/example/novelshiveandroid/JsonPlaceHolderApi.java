package com.example.novelshiveandroid;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    @GET("users")
    Call<List<User>> getUsers();

    @GET("users/{id}")
    Call<User> getUserInfos(@Path("id") int userId);

    @GET("users/{id}/favorites")
    Call<List<Story>> getUserFavorites(@Path("id") int userId);

    @GET("users/{id}/stories")
    Call<List<Story>> getUserStories(@Path("id") int userId);

    @GET("stories")
    Call<List<Story>> getStories();

    @GET("stories/{id]")
    Call<Story> getStoryInfos(@Path("id") int storyId);

    @GET("stories/{id]/storyKind")
    Call<Kind> getStoryKind(@Path("id") int storyId);

    @GET("stories/{id]/storyRating")
    Call<Rating> getStoryRating(@Path("id") int storyId);

    @GET("stories/{id]/storyHasStoryTags")
    Call<List<StoryHasStoryTag>> getStoryHasStoryTags(@Path("id") int storyId);

    @GET("Story_has_story_tags/{id}/storyTag")
    Call<Tag> getStoryTag(@Path("id") int storyHasStoryTagId);

    @GET("stories/{id]/storyChapters")
    Call<List<Chapter>> getStoryChapters(@Path("id") int storyId);

    @GET("stories/{id]/storyChapters/count")
    Call<Integer> getStoryChaptersCount(@Path("id") int storyId);

    @GET("chapters/{id}/publishedCommentaries")
    Call<List<Comment>> getChapterComments(@Path("id") int chapterId);

    @GET("chapters/{id}")
    Call<Chapter> getChapterInfos(@Path("id") int chapterId);

    @GET("tags")
    Call<List<Tag>> getTags();

    @GET("kinds")
    Call<List<Kind>> getKinds();

    @GET("ratings")
    Call<List<Rating>> getRatings();
}
