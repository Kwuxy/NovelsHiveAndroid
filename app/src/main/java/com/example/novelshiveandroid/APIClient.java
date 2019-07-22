package com.example.novelshiveandroid;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class APIClient {
    
    private static String BASE_URL = "http://ecs-first-run-alb-113054812.eu-west-3.elb.amazonaws.com:3000/api/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
}
