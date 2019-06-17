package com.example.novelshiveandroid.viewModels;

import com.example.novelshiveandroid.models.Chapter;
import com.example.novelshiveandroid.presenters.ReaderPresenter;
import com.example.novelshiveandroid.views.ReaderView;

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
    public void getChapterInfos(int chapterId) {
        Call<Chapter> call = jsonPlaceHolderApi.getChapterInfos(chapterId);
        call.enqueue(new Callback<Chapter>() {
            @Override
            public void onResponse(Call<Chapter> call, Response<Chapter> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                mReaderView.displayChapter(response.body());
            }

            @Override
            public void onFailure(Call<Chapter> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }
}
