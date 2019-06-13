package com.example.novelshiveandroid.viewModels;

import com.example.novelshiveandroid.models.Story;
import com.example.novelshiveandroid.presenters.HomePresenter;
import com.example.novelshiveandroid.views.HomeView;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.novelshiveandroid.APIClient.jsonPlaceHolderApi;

public class HomeViewModel implements HomePresenter {

    HomeView mHomeView;

    public HomeViewModel(HomeView homeView) { this.mHomeView = homeView; }

    @Override
    public void searchStories(Map<String, Object> parameters) {
        Call<List<Story>> call = jsonPlaceHolderApi.getStories(parameters);
        call.enqueue(new Callback<List<Story>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Story>> call, Response<List<Story>> response) {
                /*if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }*/
                mHomeView.displayStories(response.body());
            }

            @Override
            public void onFailure(Call<List<Story>> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }
}
