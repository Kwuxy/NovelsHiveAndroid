package com.example.novelshiveandroid.viewModels;

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
    public void getReadingChapterInfos(int chapterId) {
        Call<ReadingChapter> call = jsonPlaceHolderApi.getReadingChapterInfos(chapterId);
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
}
