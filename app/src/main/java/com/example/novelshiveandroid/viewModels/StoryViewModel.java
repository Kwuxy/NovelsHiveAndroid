package com.example.novelshiveandroid.viewModels;

import com.example.novelshiveandroid.models.Chapter;
import com.example.novelshiveandroid.models.Comment;
import com.example.novelshiveandroid.models.Kind;
import com.example.novelshiveandroid.models.Rating;
import com.example.novelshiveandroid.models.Story;
import com.example.novelshiveandroid.models.StoryHasStoryTag;
import com.example.novelshiveandroid.models.Tag;
import com.example.novelshiveandroid.presenters.StoryPresenter;
import com.example.novelshiveandroid.views.StoryView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.novelshiveandroid.APIClient.jsonPlaceHolderApi;

public class StoryViewModel implements StoryPresenter {

    StoryView mStoryView;
    List<Tag> storyTags;

    public StoryViewModel(StoryView mStoryView) {
        this.mStoryView = mStoryView;
        storyTags = new ArrayList<>();
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
        Call<List<StoryHasStoryTag>> call = jsonPlaceHolderApi.getStoryHasStoryTags(storyId);
        call.enqueue(new Callback<List<StoryHasStoryTag>>() {
            @Override
            public void onResponse(Call<List<StoryHasStoryTag>> call, Response<List<StoryHasStoryTag>> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                Call<Tag> call2;
                for(StoryHasStoryTag link : response.body()){
                    call2 = jsonPlaceHolderApi.getStoryTag(link.getId());
                    call2.enqueue(new Callback<Tag>() {
                        @Override
                        public void onResponse(Call<Tag> call2, Response<Tag> response) {
                            if (!response.isSuccessful()) {
                                System.out.print("Code : " + response.code());
                                return;
                            }
                            storyTags.add(response.body());
                        }

                        @Override
                        public void onFailure(Call<Tag> call2, Throwable t) {
                            System.out.print(t.getMessage());
                        }
                    });
                }

                mStoryView.displayStoryTags(storyTags);
            }

            @Override
            public void onFailure(Call<List<StoryHasStoryTag>> call, Throwable t) {
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
    public void getStoryChapterComments(int chapterId) {
        Call<List<Comment>> call = jsonPlaceHolderApi.getChapterComments(chapterId);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    System.out.print("Code : " + response.code());
                    return;
                }
                mStoryView.displayStoryChaptersComments(response.body());
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                System.out.print(t.getMessage());
            }
        });
    }
}
