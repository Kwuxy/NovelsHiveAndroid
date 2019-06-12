package com.example.novelshiveandroid;

import com.example.novelshiveandroid.models.Chapter;
import com.example.novelshiveandroid.models.Comment;
import com.example.novelshiveandroid.models.Favorite;
import com.example.novelshiveandroid.models.Kind;
import com.example.novelshiveandroid.models.Language;
import com.example.novelshiveandroid.models.Rating;
import com.example.novelshiveandroid.models.Status;
import com.example.novelshiveandroid.models.Story;
import com.example.novelshiveandroid.models.StoryHasStoryTag;
import com.example.novelshiveandroid.models.Tag;
import com.example.novelshiveandroid.models.Token;
import com.example.novelshiveandroid.models.Universe;
import com.example.novelshiveandroid.models.User;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface JsonPlaceHolderApi {

    //Get All Users List
    @GET("users")
    Call<List<User>> getUsers();

    //Get User Infos To Display Profile
    @GET("users/{id}")
    Call<User> getUserInfos(@Path("id") int userId);

    //Get User Favorite Stories List
    @GET("users/{id}/favorites")
    Call<List<Story>> getUserFavorites(@Path("id") int userId);

    //Get User Stories List
    @GET("users/{id}/stories")
    Call<List<Story>> getUserStories(@Path("id") int userId);

    //Get All Stories With And Without Search Sorting
    @GET("stories")
    Call<List<Story>> getStories(@QueryMap Map<String, String> parameters);

    //Get Story Infos To Display Presentation Page
    @GET("stories/{id}")
    Call<Story> getStoryInfos(@Path("id") int storyId);

    //Get Story Kind
    @GET("stories/{id}/storyKind")
    Call<Kind> getStoryKind(@Path("id") int storyId);

    //Get Story Rating
    @GET("stories/{id}/storyRating")
    Call<Rating> getStoryRating(@Path("id") int storyId);

    //Get Story Tags Links
    @GET("stories/{id}/storyHasStoryTags")
    Call<List<StoryHasStoryTag>> getStoryHasStoryTags(@Path("id") int storyId);

    //Get Tags Linked To A Story
    @GET("Story_has_story_tags/{id}/storyTag")
    Call<Tag> getStoryTag(@Path("id") int storyHasStoryTagId);

    //Get Story Chapters List
    @GET("stories/{id}/storyChapters")
    Call<List<Chapter>> getStoryChapters(@Path("id") int storyId);

    //Get Story Chapters Count
    @GET("stories/{id}/storyChapters/count")
    Call<Integer> getStoryChaptersCount(@Path("id") int storyId);

    //Get Chapter Published Comments
    @GET("chapters/{id}/publishedCommentaries")
    Call<List<Comment>> getChapterComments(@Path("id") int chapterId);

    //Get Chapter Infos To Display It
    @GET("chapters/{id}")
    Call<Chapter> getChapterInfos(@Path("id") int chapterId);

    //Get All Tags For Story Searching Filters
    @GET("tags")
    Call<List<Tag>> getTags();

    //Get All Kinds For Story Searching Filters
    @GET("kinds")
    Call<List<Kind>> getKinds();

    //Get All Ratings For Story Searching Filters
    @GET("ratings")
    Call<List<Rating>> getRatings();

    //Get All Universes For Story Searching Filters
    @GET("universes")
    Call<List<Universe>> getUniverses();

    //Get All Languages For Story Searching Filters
    @GET("languages")
    Call<List<Language>> getLanguages();

    //Get All Status For Story Searching Filters
    @GET("status")
    Call<List<Status>> getStatus();

    //Register A New User
    @POST("users")
    Call<User> registerUser(@Body User user);

    //User Login
    @FormUrlEncoded
    @POST("users/login")
    Call<Token> loginUser(@Field("username") String email, @Field("password") String password);

    //Create A Published Comment About A Chapter (Drafted Commentaries not need in Android App)
    @POST("published_commentaries")
    Call<Comment> createComment(@Body Comment comment);

    //Add Story as Favorite
    @POST("favorites")
    Response addToFavorites(@Body Favorite favorite);

    //Change User Reading Environment (or Update User Infos If Needed Later)
    @PATCH("users/{id}")
    Call<User> updateUser(@Path("id") int id, @Body User user);
}
