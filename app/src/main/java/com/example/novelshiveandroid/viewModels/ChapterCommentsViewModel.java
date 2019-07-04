package com.example.novelshiveandroid.viewModels;

import com.example.novelshiveandroid.models.Comment;
import com.example.novelshiveandroid.presenters.ChapterCommentsPresenter;
import com.example.novelshiveandroid.views.ChapterCommentsView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.novelshiveandroid.APIClient.jsonPlaceHolderApi;

public class ChapterCommentsViewModel implements ChapterCommentsPresenter {

    ChapterCommentsView mchapterCommentsView;

    public ChapterCommentsViewModel(ChapterCommentsView mchapterCommentsView) {
        this.mchapterCommentsView = mchapterCommentsView;
    }

    @Override
    public void getChapterComments(int chapterId) {
        Call<List<Comment>> chapterComments = jsonPlaceHolderApi.getChapterComments(chapterId);
        chapterComments.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                mchapterCommentsView.displayChapterComments(response.body());
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }
}
