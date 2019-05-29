package com.example.novelshiveandroid;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("users")
    Call<List<User>> getUsers();
}
