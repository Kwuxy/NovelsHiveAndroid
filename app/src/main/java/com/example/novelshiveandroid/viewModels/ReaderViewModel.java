package com.example.novelshiveandroid.viewModels;

import com.example.novelshiveandroid.Globals;
import com.example.novelshiveandroid.models.Favorite;
import com.example.novelshiveandroid.models.ReadingChapter;
import com.example.novelshiveandroid.presenters.ReaderPresenter;
import com.example.novelshiveandroid.views.ReaderView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.novelshiveandroid.APIClient.jsonPlaceHolderApi;

public class ReaderViewModel implements ReaderPresenter {

    ReaderView mReaderView;

    public ReaderViewModel(ReaderView mReaderView) {
        this.mReaderView = mReaderView;
    }

    @Override
    public void getReadingChapterInfos(int chapterId, int userId) {
        Call<ReadingChapter> call = jsonPlaceHolderApi.getReadingChapterInfos(chapterId, userId);
        call.enqueue(new Callback<ReadingChapter>() {
            @Override
            public void onResponse(Call<ReadingChapter> call, Response<ReadingChapter> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                mReaderView.displayReadingChapter(response.body());
            }

            @Override
            public void onFailure(Call<ReadingChapter> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }

    @Override
    public void addToFavorites(int userId, int storyId) {
        Favorite favorite = new Favorite(userId, storyId);
        String tokenValue = Globals.getCurrentToken().getId();
        Call<Favorite> call = jsonPlaceHolderApi.addToFavorites(tokenValue, favorite);
        call.enqueue(new Callback<Favorite>() {
            @Override
            public void onResponse(Call<Favorite> call, Response<Favorite> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                mReaderView.getFavoriteId(response.body());
                mReaderView.displayFavoriteAdding();
            }

            @Override
            public void onFailure(Call<Favorite> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }

    @Override
    public void removeToFavorites(int favoriteId) {
        String tokenValue = Globals.getCurrentToken().getId();
        Call<Void> call = jsonPlaceHolderApi.removeToFavorites(tokenValue, favoriteId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                mReaderView.displayFavoriteDeleting();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }

    @Override
    public void checkIfStoryInUserFavorites(int userId, int storyId) {
        Call<Favorite> call = jsonPlaceHolderApi.checkStoryInUserFavorites(userId, storyId);
        call.enqueue(new Callback<Favorite>() {
            @Override
            public void onResponse(Call<Favorite> call, Response<Favorite> response) {
                if (response.isSuccessful()) {
                    mReaderView.getFavoriteId(response.body());
                }
                mReaderView.setInFavoriteValue(response.isSuccessful());
            }

            @Override
            public void onFailure(Call<Favorite> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }
}
