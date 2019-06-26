package com.example.novelshiveandroid;


import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.novelshiveandroid.models.Kind;
import com.example.novelshiveandroid.models.Language;
import com.example.novelshiveandroid.models.Rating;
import com.example.novelshiveandroid.models.Status;
import com.example.novelshiveandroid.models.Tag;
import com.example.novelshiveandroid.models.Universe;
import com.example.novelshiveandroid.presenters.SearchPresenter;
import com.example.novelshiveandroid.viewModels.SearchViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements com.example.novelshiveandroid.views.SearchView {

    SearchPresenter mSearchPresenter;


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mSearchPresenter = new SearchViewModel(SearchFragment.this);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
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
