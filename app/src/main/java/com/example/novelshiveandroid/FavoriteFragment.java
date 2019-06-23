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
import com.example.novelshiveandroid.models.Story;
import com.example.novelshiveandroid.presenters.FavoritePresenter;
import com.example.novelshiveandroid.views.FavoriteView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.novelshiveandroid.Globals.KEY_STORY_ID;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment implements FavoriteView {

    private RecyclerView rvStories;
    private ArrayList<Story> stories;
    private FavoriteStoriesAdapter favoriteStoriesAdapter;
    private FavoritePresenter favoritePresenter;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Test adapter
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);

        /*
        rvStories = rootView.findViewById(R.id.rvStories);
        rvStories.setLayoutManager(new LinearLayoutManager(getActivity()));

        stories = new ArrayList<Story>();
        for (int i = 1; i <= 10; i++) {
            stories.add(new Story("Story " + i, new Date()));
        }
        favoriteStoriesAdapter = new FavoriteStoriesAdapter(this, stories);
        rvStories.setAdapter(favoriteStoriesAdapter);
        rvStories.setItemAnimator(new DefaultItemAnimator());
        */
        return rootView;
    }

    public void onStoryItemClick(int position) {
        Intent storyDetailsIntent = new Intent(getActivity(), StoryDetailsActivity.class);
        storyDetailsIntent.putExtra(KEY_STORY_ID, stories.get(position).getId());
        startActivity(storyDetailsIntent);
    }

    @Override
    public void displayUserFavorites(List<Story> favorites) {
        //stories.addAll(favorites);
        //favoriteStoriesAdapter.notifyDataSetChanged();
    }
}
