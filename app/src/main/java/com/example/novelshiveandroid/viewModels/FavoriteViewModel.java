package com.example.novelshiveandroid.viewModels;

import com.example.novelshiveandroid.models.Story;
import com.example.novelshiveandroid.presenters.FavoritePresenter;
import com.example.novelshiveandroid.views.FavoriteView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.novelshiveandroid.APIClient.jsonPlaceHolderApi;

public class FavoriteViewModel implements FavoritePresenter {

    FavoriteView mFavoriteView;

    public FavoriteViewModel(FavoriteView mFavoriteView) {
        this.mFavoriteView = mFavoriteView;
    }

    @Override
    public void getUserFavorites(int userId) {
        Call<List<Story>> call = jsonPlaceHolderApi.getUserFavorites(userId);
        call.enqueue(new Callback<List<Story>>() {
            @Override
            public void onResponse(Call<List<Story>> call, Response<List<Story>> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                mFavoriteView.displayUserFavorites(response.body());
            }

            @Override
            public void onFailure(Call<List<Story>> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }
}
