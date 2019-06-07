package com.example.novelshiveandroid.controllers;

import com.example.novelshiveandroid.JsonPlaceHolderApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class ControllerConfig {

    // TODO: Change BASE_URL value for prod
    private static String BASE_URL = "http://localhost:3000/api/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
}
