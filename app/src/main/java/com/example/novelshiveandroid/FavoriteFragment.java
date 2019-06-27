package com.example.novelshiveandroid;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.novelshiveandroid.adapter.FavoriteStoriesAdapter;
import com.example.novelshiveandroid.adapter.HomeStoryAdapter;
import com.example.novelshiveandroid.models.Story;
import com.example.novelshiveandroid.presenters.FavoritePresenter;
import com.example.novelshiveandroid.viewModels.FavoriteViewModel;
import com.example.novelshiveandroid.views.FavoriteView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.novelshiveandroid.Globals.KEY_STORY_ID;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment implements FavoriteView {

    private View rootView;
    private RecyclerView rvStories;
    private ArrayList<Story> stories;
    private FavoriteStoriesAdapter favoriteStoriesAdapter;
    private FavoritePresenter mFavoritePresenter;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_favorite, container, false);
        initUI();

        mFavoritePresenter = new FavoriteViewModel(this);
        int userId = Globals.getInstance().getCurrentToken().getUserId();
        // Request Data
        mFavoritePresenter.getUserFavorites(userId);
        return rootView;
    }

    public void initUI() {
        rvStories = rootView.findViewById(R.id.rvStories);
        rvStories.setLayoutManager(new LinearLayoutManager(getActivity()));

        stories = new ArrayList<>();
        favoriteStoriesAdapter = new FavoriteStoriesAdapter(this, stories);
        rvStories.setAdapter(favoriteStoriesAdapter);
    }

    public void onStoryItemClick(int position) {
        Intent storyDetailsIntent = new Intent(getActivity(), StoryDetailsActivity.class);
        storyDetailsIntent.putExtra(KEY_STORY_ID, stories.get(position).getId());
        startActivity(storyDetailsIntent);
    }

    @Override
    public void displayUserFavorites(List<Story> favorites) {
        if (!favorites.isEmpty()) {
            stories.clear();
            stories.addAll(favorites);
            favoriteStoriesAdapter.notifyDataSetChanged();
        }
    }
}
