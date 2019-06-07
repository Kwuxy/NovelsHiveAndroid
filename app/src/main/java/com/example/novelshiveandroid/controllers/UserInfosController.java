package com.example.novelshiveandroid.controllers;

import com.example.novelshiveandroid.models.Story;
import com.example.novelshiveandroid.models.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.novelshiveandroid.controllers.ControllerConfig.jsonPlaceHolderApi;

public abstract class UserInfosController {

    //Get User Infos To Display Profile
    public static User getUserInfos(int userId){
        final List<User> onlyOneUser = new ArrayList<>();
        Call<User> call = jsonPlaceHolderApi.getUserInfos(userId);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                onlyOneUser.add(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });

        return onlyOneUser.get(0);
    }

    //Get User Favorite Stories List
    public static List<Story> getUserFavorites(int userId){
        final List<Story> userFavorites = new ArrayList<>();
        Call<List<Story>> call = jsonPlaceHolderApi.getUserFavorites(userId);
        call.enqueue(new Callback<List<Story>>() {
            @Override
            public void onResponse(Call<List<Story>> call, Response<List<Story>> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                userFavorites.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Story>> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });

        return userFavorites;

    }

    //Get User Stories List
    public static List<Story> getUserStories(int userId){
        final List<Story> userStories = new ArrayList<>();
        Call<List<Story>> call = jsonPlaceHolderApi.getUserStories(userId);
        call.enqueue(new Callback<List<Story>>() {
            @Override
            public void onResponse(Call<List<Story>> call, Response<List<Story>> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                userStories.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Story>> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });

        return userStories;

    }

}
