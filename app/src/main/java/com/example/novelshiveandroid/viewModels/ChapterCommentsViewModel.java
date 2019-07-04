package com.example.novelshiveandroid.viewModels;

import com.example.novelshiveandroid.models.PublishedCommentList;
import com.example.novelshiveandroid.presenters.ChapterCommentsPresenter;
import com.example.novelshiveandroid.views.ChapterCommentsView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.novelshiveandroid.APIClient.jsonPlaceHolderApi;

public class ChapterCommentsViewModel implements ChapterCommentsPresenter {

    ChapterCommentsView mChapterCommentsView;

    public ChapterCommentsViewModel(ChapterCommentsView mchapterCommentsView) {
        this.mChapterCommentsView = mchapterCommentsView;
    }

    @Override
    public void getChapterComments(int chapterId) {
        Call<PublishedCommentList> chapterComments = jsonPlaceHolderApi.getChapterComments(chapterId);
        chapterComments.enqueue(new Callback<PublishedCommentList>() {
            @Override
            public void onResponse(Call<PublishedCommentList> call, Response<PublishedCommentList> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                mChapterCommentsView.displayChapterComments(response.body().getListPublishedComment());
            }

            @Override
            public void onFailure(Call<PublishedCommentList> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }
}
