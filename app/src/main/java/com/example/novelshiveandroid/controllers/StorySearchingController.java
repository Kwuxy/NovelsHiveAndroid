package com.example.novelshiveandroid.controllers;

import com.example.novelshiveandroid.models.Kind;
import com.example.novelshiveandroid.models.Language;
import com.example.novelshiveandroid.models.Rating;
import com.example.novelshiveandroid.models.Status;
import com.example.novelshiveandroid.models.Story;
import com.example.novelshiveandroid.models.Tag;
import com.example.novelshiveandroid.models.Universe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.novelshiveandroid.controllers.ControllerConfig.jsonPlaceHolderApi;

public abstract class StorySearchingController {

    //Get Stories With Searching Filters Parameters
    public static List<Story> getStories(Map<String, String> parameters){
        final List<Story> stories = new ArrayList<>();
        Call<List<Story>> call = jsonPlaceHolderApi.getStories(parameters);
        call.enqueue(new Callback<List<Story>>() {
            @Override
            public void onResponse(Call<List<Story>> call, Response<List<Story>> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }

                stories.clear();
                stories.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Story>> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });

        return stories;

    }

    //Get All Tags For Story Searching Filters
    public static List<Tag> getTags(){
        final List<Tag> tags = new ArrayList<>();
        Call<List<Tag>> call = jsonPlaceHolderApi.getTags();
        call.enqueue(new Callback<List<Tag>>() {
            @Override
            public void onResponse(Call<List<Tag>> call, Response<List<Tag>> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }

                tags.clear();
                tags.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Tag>> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });

        return tags;
    }

    //Get All Kinds For Story Searching Filters
    public static List<Kind> getKinds(){
        final List<Kind> kinds = new ArrayList<>();
        Call<List<Kind>> call = jsonPlaceHolderApi.getKinds();
        call.enqueue(new Callback<List<Kind>>() {
            @Override
            public void onResponse(Call<List<Kind>> call, Response<List<Kind>> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }

                kinds.clear();
                kinds.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Kind>> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });

        return kinds;
    }

    //Get All Ratings For Story Searching Filters
    public static List<Rating> getRatings(){
        final List<Rating> ratings = new ArrayList<>();
        Call<List<Rating>> call = jsonPlaceHolderApi.getRatings();
        call.enqueue(new Callback<List<Rating>>() {
            @Override
            public void onResponse(Call<List<Rating>> call, Response<List<Rating>> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }

                ratings.clear();
                ratings.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Rating>> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });

        return ratings;
    }

    //Get All Universes For Story Searching Filters
    public static List<Universe> getUniverses(){
        final List<Universe> universes = new ArrayList<>();
        Call<List<Universe>> call = jsonPlaceHolderApi.getUniverses();
        call.enqueue(new Callback<List<Universe>>() {
            @Override
            public void onResponse(Call<List<Universe>> call, Response<List<Universe>> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }

                universes.clear();
                universes.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Universe>> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });

        return universes;
    }

    //Get All Languages For Story Searching Filters
    public static List<Language> getLanguages(){
        final List<Language> languages = new ArrayList<>();
        Call<List<Language>> call = jsonPlaceHolderApi.getLanguages();
        call.enqueue(new Callback<List<Language>>() {
            @Override
            public void onResponse(Call<List<Language>> call, Response<List<Language>> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }

                languages.clear();
                languages.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Language>> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });

        return languages;
    }

    //Get All Status For Story Searching Filters
    public static List<Status> getStatus(){
        final List<Status> status = new ArrayList<>();
        Call<List<Status>> call = jsonPlaceHolderApi.getStatus();
        call.enqueue(new Callback<List<Status>>() {
            @Override
            public void onResponse(Call<List<Status>> call, Response<List<Status>> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }

                status.clear();
                status.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Status>> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });

        return status;
    }

}
