package com.example.novelshiveandroid.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.novelshiveandroid.R;
import com.example.novelshiveandroid.activities.StoryDetailsActivity;
import com.example.novelshiveandroid.adapter.HomeStoryAdapter;
import com.example.novelshiveandroid.models.Story;
import com.example.novelshiveandroid.presenters.HomePresenter;
import com.example.novelshiveandroid.viewModels.HomeViewModel;
import com.example.novelshiveandroid.views.HomeView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.novelshiveandroid.Globals.KEY_STORY_ID;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeView {

    private View rootView;
    private RecyclerView rvAllStories;
    private ArrayList<Story> allStories;
    private HomeStoryAdapter homeStoryAdapter;
    private HomePresenter mHomePresenter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        initUI();

        mHomePresenter = new HomeViewModel(HomeFragment.this);
        // Request data
        mHomePresenter.searchStories(Collections.<String, Object>emptyMap());

        return rootView;
    }

    private void initUI() {
        rvAllStories = rootView.findViewById(R.id.rv_all_stories);
        rvAllStories.setLayoutManager(new LinearLayoutManager(getActivity()));

        allStories = new ArrayList<>();
        homeStoryAdapter = new HomeStoryAdapter(this, allStories);
        rvAllStories.setAdapter(homeStoryAdapter);
    }

    public void onStoryItemClick(int position) {
        Intent storyDetailsIntent = new Intent(getActivity(), StoryDetailsActivity.class);
        storyDetailsIntent.putExtra(KEY_STORY_ID, allStories.get(position).getId());
        startActivity(storyDetailsIntent);
    }

    @Override
    public void displayStories(List<Story> stories) {
        if (!stories.isEmpty()) {
            allStories.clear();
            allStories.addAll(stories);
            homeStoryAdapter.notifyDataSetChanged();
        }
    }
}
