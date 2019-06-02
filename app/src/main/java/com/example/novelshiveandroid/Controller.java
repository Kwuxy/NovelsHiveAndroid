package com.example.novelshiveandroid;

import com.example.novelshiveandroid.models.Story;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class Controller {

    // TODO: Change BASE_URL value for prod
    private String BASE_URL = "http://localhost:3000/api/";

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

    public List<Story> getStories(Map<String, String> parameters){
        final List<Story> stories = Collections.emptyList();
        Call<List<Story>> call = jsonPlaceHolderApi.getStories(parameters);
        call.enqueue(new Callback<List<Story>>() {
            @Override
            public void onResponse(Call<List<Story>> call, Response<List<Story>> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }

                stories.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Story>> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });

        return stories;

    }
}
