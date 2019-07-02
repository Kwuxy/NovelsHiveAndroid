package com.example.novelshiveandroid.viewModels;

import com.example.novelshiveandroid.Globals;
import com.example.novelshiveandroid.models.FavoriteList;
import com.example.novelshiveandroid.presenters.FavoritePresenter;
import com.example.novelshiveandroid.views.FavoriteView;

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
        String tokenValue = Globals.getInstance().getCurrentToken().getId();
        Call<FavoriteList> call = jsonPlaceHolderApi.getUserFavorites(tokenValue, userId);
        call.enqueue(new Callback<FavoriteList>() {
            @Override
            public void onResponse(Call<FavoriteList> call, Response<FavoriteList> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                mFavoriteView.displayUserFavorites(response.body().getFavoriteStories());
            }

            @Override
            public void onFailure(Call<FavoriteList> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }
}
