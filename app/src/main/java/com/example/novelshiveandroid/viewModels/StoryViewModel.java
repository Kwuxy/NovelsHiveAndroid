package com.example.novelshiveandroid.viewModels;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.novelshiveandroid.Globals;
import com.example.novelshiveandroid.models.Chapter;
import com.example.novelshiveandroid.models.Favorite;
import com.example.novelshiveandroid.models.Kind;
import com.example.novelshiveandroid.models.PublishedCommentList;
import com.example.novelshiveandroid.models.Rating;
import com.example.novelshiveandroid.models.Story;
import com.example.novelshiveandroid.models.TagList;
import com.example.novelshiveandroid.presenters.StoryPresenter;
import com.example.novelshiveandroid.views.StoryView;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.novelshiveandroid.APIClient.jsonPlaceHolderApi;

public class StoryViewModel implements StoryPresenter {

    StoryView mStoryView;

    public StoryViewModel(StoryView mStoryView) {
        this.mStoryView = mStoryView;
    }

    @Override
    public void getStoryInfos(final int storyId) {
        Call<Story> call = jsonPlaceHolderApi.getStoryInfos(storyId);
        call.enqueue(new Callback<Story>() {
            @Override
            public void onResponse(Call<Story> call, Response<Story> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                mStoryView.displayStoryInfos(response.body());
                getStoryImage(response.body().getPanel());
            }

            @Override
            public void onFailure(Call<Story> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }

    @Override
    public void getStoryKind(int storyId) {
        Call<Kind> call = jsonPlaceHolderApi.getStoryKind(storyId);
        call.enqueue(new Callback<Kind>() {
            @Override
            public void onResponse(Call<Kind> call, Response<Kind> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                mStoryView.displayStoryKind(response.body());
            }

            @Override
            public void onFailure(Call<Kind> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }

    @Override
    public void getStoryRating(int storyId) {
        Call<Rating> call = jsonPlaceHolderApi.getStoryRating(storyId);
        call.enqueue(new Callback<Rating>() {
            @Override
            public void onResponse(Call<Rating> call, Response<Rating> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                mStoryView.displayStoryRating(response.body());
            }

            @Override
            public void onFailure(Call<Rating> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }

    @Override
    public void getStoryTags(int storyId) {
        Call<TagList> call = jsonPlaceHolderApi.getStoryTags(storyId);
        call.enqueue(new Callback<TagList>() {
            @Override
            public void onResponse(Call<TagList> call, Response<TagList> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                mStoryView.displayStoryTags(response.body().getStoryTags());
            }

            @Override
            public void onFailure(Call<TagList> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }

    @Override
    public void getStoryImage(String filePath) {
        Call<LinkedTreeMap> call = jsonPlaceHolderApi.getStoryImageBuffer("storyImage", filePath);
        call.enqueue(new Callback<LinkedTreeMap>() {
            @Override
            public void onResponse(Call<LinkedTreeMap> call, Response<LinkedTreeMap> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                LinkedTreeMap result = (LinkedTreeMap)response.body().get("result");
                LinkedTreeMap content = (LinkedTreeMap)result.get("content");
                ArrayList<Double> imageData = (ArrayList<Double>)content.get("data");
                byte[] data = new byte[imageData.size()];
                for(int i = 0; i < imageData.size(); i++)
                    data[i] = imageData.get(i).byteValue();
                Bitmap bmpImage = BitmapFactory.decodeByteArray(data,0, data.length);
                mStoryView.displayStoryImage(bmpImage);
            }

            @Override
            public void onFailure(Call<LinkedTreeMap> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }

    @Override
    public void getStoryChapters(int storyId) {
        Call<List<Chapter>> call = jsonPlaceHolderApi.getStoryChapters(storyId);
        call.enqueue(new Callback<List<Chapter>>() {
            @Override
            public void onResponse(Call<List<Chapter>> call, Response<List<Chapter>> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                mStoryView.displayStoryChapters(response.body());
            }

            @Override
            public void onFailure(Call<List<Chapter>> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }

    @Override
    public void getStoryChaptersCount(int storyId){
        Call<Integer> call = jsonPlaceHolderApi.getStoryChaptersCount(storyId);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                mStoryView.displayStoryChaptersCount(response.body());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
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
                mStoryView.getFavoriteId(response.body());
                mStoryView.displayFavoriteAdding();
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
                mStoryView.displayFavoriteDeleting();
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
                    mStoryView.getFavoriteId(response.body());
                }
                mStoryView.setInFavoriteValue(response.isSuccessful());
            }

            @Override
            public void onFailure(Call<Favorite> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }
}
