package com.example.novelshiveandroid.viewModels;

import com.example.novelshiveandroid.models.Kind;
import com.example.novelshiveandroid.models.Language;
import com.example.novelshiveandroid.models.Rating;
import com.example.novelshiveandroid.models.Status;
import com.example.novelshiveandroid.models.Story;
import com.example.novelshiveandroid.models.Tag;
import com.example.novelshiveandroid.models.Universe;
import com.example.novelshiveandroid.presenters.SearchPresenter;
import com.example.novelshiveandroid.views.SearchView;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.novelshiveandroid.APIClient.jsonPlaceHolderApi;

public class SearchViewModel implements SearchPresenter {

    SearchView mSearchView;

    public SearchViewModel(SearchView mSearchView) {
        this.mSearchView = mSearchView;
    }

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
                mSearchView.displayStories(response.body());
            }

            @Override
            public void onFailure(Call<List<Story>> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }

    @Override
    public void getTags() {
        Call<List<Tag>> call = jsonPlaceHolderApi.getTags();
        call.enqueue(new Callback<List<Tag>>() {
            @Override
            public void onResponse(Call<List<Tag>> call, Response<List<Tag>> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                mSearchView.displayTags(response.body());
            }

            @Override
            public void onFailure(Call<List<Tag>> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }

    @Override
    public void getKinds() {
        Call<List<Kind>> call = jsonPlaceHolderApi.getKinds();
        call.enqueue(new Callback<List<Kind>>() {
            @Override
            public void onResponse(Call<List<Kind>> call, Response<List<Kind>> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                mSearchView.displayKinds(response.body());
            }

            @Override
            public void onFailure(Call<List<Kind>> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }

    @Override
    public void getRatings() {
        Call<List<Rating>> call = jsonPlaceHolderApi.getRatings();
        call.enqueue(new Callback<List<Rating>>() {
            @Override
            public void onResponse(Call<List<Rating>> call, Response<List<Rating>> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                mSearchView.displayRatings(response.body());
            }

            @Override
            public void onFailure(Call<List<Rating>> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }

    @Override
    public void getUniverses() {
        Call<List<Universe>> call = jsonPlaceHolderApi.getUniverses();
        call.enqueue(new Callback<List<Universe>>() {
            @Override
            public void onResponse(Call<List<Universe>> call, Response<List<Universe>> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                mSearchView.displayUniverses(response.body());
            }

            @Override
            public void onFailure(Call<List<Universe>> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }

    @Override
    public void getLanguages() {
        Call<List<Language>> call = jsonPlaceHolderApi.getLanguages();
        call.enqueue(new Callback<List<Language>>() {
            @Override
            public void onResponse(Call<List<Language>> call, Response<List<Language>> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                mSearchView.displayLanguages(response.body());
            }

            @Override
            public void onFailure(Call<List<Language>> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }

    @Override
    public void getStatus() {
        Call<List<Status>> call = jsonPlaceHolderApi.getStatus();
        call.enqueue(new Callback<List<Status>>() {
            @Override
            public void onResponse(Call<List<Status>> call, Response<List<Status>> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                mSearchView.displayStatus(response.body());
            }

            @Override
            public void onFailure(Call<List<Status>> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }
}
