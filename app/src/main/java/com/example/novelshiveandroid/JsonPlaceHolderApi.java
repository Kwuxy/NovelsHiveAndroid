package com.example.novelshiveandroid;

import com.example.novelshiveandroid.models.Chapter;
import com.example.novelshiveandroid.models.Comment;
import com.example.novelshiveandroid.models.Kind;
import com.example.novelshiveandroid.models.Language;
import com.example.novelshiveandroid.models.Rating;
import com.example.novelshiveandroid.models.Status;
import com.example.novelshiveandroid.models.Story;
import com.example.novelshiveandroid.models.StoryHasStoryTag;
import com.example.novelshiveandroid.models.Tag;
import com.example.novelshiveandroid.models.Universe;
import com.example.novelshiveandroid.models.User;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

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
    Call<List<Story>> getStories(@QueryMap Map<String, String> parameters);

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

    @GET("universes")
    Call<List<Universe>> getUniverses();

    @GET("languages")
    Call<List<Language>> getLanguages();

    @GET("status")
    Call<List<Status>> getStatus();

    @POST("users")
    Call<User> createUser(@Body User user);

    @POST("users/login")
    Response loginUser(String email, String password);

}
