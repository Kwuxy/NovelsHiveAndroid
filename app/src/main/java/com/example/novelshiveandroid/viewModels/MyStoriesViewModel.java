package com.example.novelshiveandroid.viewModels;

import com.example.novelshiveandroid.Globals;
import com.example.novelshiveandroid.models.Story;
import com.example.novelshiveandroid.presenters.MyStoriesPresenter;
import com.example.novelshiveandroid.views.MyStoriesView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.novelshiveandroid.APIClient.jsonPlaceHolderApi;

public class MyStoriesViewModel implements MyStoriesPresenter {

    MyStoriesView mMyStoriesView;

    public MyStoriesViewModel(MyStoriesView mMyStoriesView) {
        this.mMyStoriesView = mMyStoriesView;
    }

    @Override
    public void getUserStories(int userId) {
        String tokenValue = Globals.getCurrentToken().getId();
        Call<List<Story>> call = jsonPlaceHolderApi.getUserStories(tokenValue, userId);
        call.enqueue(new Callback<List<Story>>() {
            @Override
            public void onResponse(Call<List<Story>> call, Response<List<Story>> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                mMyStoriesView.displayUserStories(response.body());
            }

            @Override
            public void onFailure(Call<List<Story>> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }
}
