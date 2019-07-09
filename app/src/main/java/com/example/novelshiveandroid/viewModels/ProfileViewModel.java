package com.example.novelshiveandroid.viewModels;

import com.example.novelshiveandroid.Globals;
import com.example.novelshiveandroid.models.User;
import com.example.novelshiveandroid.presenters.ProfilePresenter;
import com.example.novelshiveandroid.views.ProfileView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.novelshiveandroid.APIClient.jsonPlaceHolderApi;

public class ProfileViewModel implements ProfilePresenter {

    ProfileView mProfileView;

    public ProfileViewModel(ProfileView mProfileView) {
        this.mProfileView = mProfileView;
    }

    @Override
    public void getUserInfos(int userId) {
        String tokenValue = Globals.getCurrentToken().getId();
        Call<User> call = jsonPlaceHolderApi.getUserInfos(tokenValue, userId);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                mProfileView.displayUserProfile(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }
}
