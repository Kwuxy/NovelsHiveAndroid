package com.example.novelshiveandroid.viewModels;

import com.example.novelshiveandroid.Globals;
import com.example.novelshiveandroid.models.Comment;
import com.example.novelshiveandroid.presenters.CommentWriterPresenter;
import com.example.novelshiveandroid.views.CommentWriterView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.novelshiveandroid.APIClient.jsonPlaceHolderApi;

public class CommentWriterViewModel implements CommentWriterPresenter {

    CommentWriterView mCommentWriterView;

    public CommentWriterViewModel(CommentWriterView mCommentWriterView) {
        this.mCommentWriterView = mCommentWriterView;
    }

    @Override
    public void writeComment(String commentContent, int userWriterId, int storyChapterId) {
        Comment comment = new Comment(commentContent, userWriterId, storyChapterId);
        String tokenValue = Globals.getInstance().getCurrentToken().getId();
        Call<Comment> call = jsonPlaceHolderApi.createComment(tokenValue, comment);
        call.enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(Call<Comment> call, Response<Comment> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                mCommentWriterView.commentWritingSuccess();
            }

            @Override
            public void onFailure(Call<Comment> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }
}
