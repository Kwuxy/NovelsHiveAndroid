package com.example.novelshiveandroid;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.novelshiveandroid.models.Story;
import com.example.novelshiveandroid.presenters.HomePresenter;
import com.example.novelshiveandroid.viewModels.HomeViewModel;
import com.example.novelshiveandroid.views.HomeView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeView {

    HomePresenter mHomePresenter;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mHomePresenter = new HomeViewModel(HomeFragment.this);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void displayStories(List<Story> stories) {

    }
}
