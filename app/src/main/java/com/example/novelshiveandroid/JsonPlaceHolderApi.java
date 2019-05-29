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

    @GET("chapters")
    Call<List<Chapter>> getChapters();

    @GET("tags")
    Call<List<Tag>> getTags();
}
