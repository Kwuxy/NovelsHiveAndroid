package com.example.novelshiveandroid.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.novelshiveandroid.R;
import com.example.novelshiveandroid.activities.StoryDetailsActivity;
import com.example.novelshiveandroid.adapter.FragmentStoriesAdapter;
import com.example.novelshiveandroid.models.Kind;
import com.example.novelshiveandroid.models.Language;
import com.example.novelshiveandroid.models.Rating;
import com.example.novelshiveandroid.models.Status;
import com.example.novelshiveandroid.models.Story;
import com.example.novelshiveandroid.models.Tag;
import com.example.novelshiveandroid.models.Universe;
import com.example.novelshiveandroid.presenters.SearchPresenter;
import com.example.novelshiveandroid.viewModels.SearchViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.novelshiveandroid.Globals.KEY_STORY_ID;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements com.example.novelshiveandroid.views.SearchView {

    private View rootView;
    private RecyclerView rvSearchStories;
    private ArrayList<Story> searchStories;
    private FragmentStoriesAdapter fragmentStoriesAdapter;
    SearchPresenter mSearchPresenter;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_search, container, false);
        initUI();

        mSearchPresenter = new SearchViewModel(SearchFragment.this);

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_search, container, false);
        return rootView;
    }

    private void initUI() {
        rvSearchStories = rootView.findViewById(R.id.rv_search_stories);
        rvSearchStories.setLayoutManager(new LinearLayoutManager(getActivity()));

        searchStories = new ArrayList<>();
        fragmentStoriesAdapter = new FragmentStoriesAdapter(this, searchStories);
        rvSearchStories.setAdapter(fragmentStoriesAdapter);
        // On cache la recycler view
        rvSearchStories.setVisibility(View.GONE);
    }

    public void onStoryItemClick(int position) {
        Intent storyDetailsIntent = new Intent(getActivity(), StoryDetailsActivity.class);
        storyDetailsIntent.putExtra(KEY_STORY_ID, searchStories.get(position).getId());
        startActivity(storyDetailsIntent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu, menu);

        //SearchManager searchManager = (SearchManager)getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_toolbarSearch).getActionView();
        searchView.setQueryHint(getString(R.string.search_hint));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void displayTags(List<Tag> tags) {

    }

    @Override
    public void displayKinds(List<Kind> kinds) {

    }

    @Override
    public void displayRatings(List<Rating> ratings) {

    }

    @Override
    public void displayUniverses(List<Universe> universes) {

    }

    @Override
    public void displayLanguages(List<Language> languages) {

    }

    @Override
    public void displayStatus(List<Status> status) {

    }
}
