package com.example.novelshiveandroid.viewModels;

import android.util.Log;

import com.example.novelshiveandroid.Globals;
import com.example.novelshiveandroid.models.Favorite;
import com.example.novelshiveandroid.models.FavoriteList;
import com.example.novelshiveandroid.models.Story;
import com.example.novelshiveandroid.presenters.FavoritePresenter;
import com.example.novelshiveandroid.views.FavoriteView;

import java.io.IOException;
import java.util.ArrayList;
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
        String tokenValue = Globals.getInstance().getCurrentToken().getId();
        Call<FavoriteList> call = jsonPlaceHolderApi.getUserFavorites(tokenValue, userId);
        call.enqueue(new Callback<FavoriteList>() {
            @Override
            public void onResponse(Call<FavoriteList> call, Response<FavoriteList> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                Log.i("TEST_FAV_STORY", "Size : " + response.body().getFavoriteStories().size());
                mFavoriteView.displayUserFavorites(response.body().getFavoriteStories());
            }

            @Override
            public void onFailure(Call<FavoriteList> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }
}
