package com.example.novelshiveandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.novelshiveandroid.Globals;
import com.example.novelshiveandroid.R;
import com.example.novelshiveandroid.adapter.FragmentStoriesAdapter;
import com.example.novelshiveandroid.adapter.MyStoriesAdapter;
import com.example.novelshiveandroid.models.Story;
import com.example.novelshiveandroid.presenters.MyStoriesPresenter;
import com.example.novelshiveandroid.viewModels.MyStoriesViewModel;
import com.example.novelshiveandroid.views.MyStoriesView;

import java.util.ArrayList;
import java.util.List;

import static com.example.novelshiveandroid.Globals.KEY_STORY_ID;

public class MyStoriesActivity extends AppCompatActivity implements MyStoriesView {

    private Toolbar myToolbar;
    private RecyclerView rvMyStories;
    private ArrayList<Story> myStories;
    private MyStoriesAdapter myStoriesAdapter;
    private MyStoriesPresenter myStoriesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stories);

        this.configureToolbar();
        this.initUI();

        myStoriesPresenter = new MyStoriesViewModel(this);
        myStoriesPresenter.getUserStories(Globals.getCurrentToken().getUserId());
    }

    private void configureToolbar() {
        myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("My stories");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initUI() {
        this.rvMyStories = findViewById(R.id.rv_my_stories);
        rvMyStories.setLayoutManager(new LinearLayoutManager(this));

        myStories = new ArrayList<>();
        myStoriesAdapter = new MyStoriesAdapter(this, myStories);
        rvMyStories.setAdapter(myStoriesAdapter);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    public void onStoryItemClick(int position) {
        Intent storyDetailsIntent = new Intent(this, StoryDetailsActivity.class);
        storyDetailsIntent.putExtra(KEY_STORY_ID, myStories.get(position).getId());
        startActivity(storyDetailsIntent);
    }

    @Override
    public void displayUserStories(List<Story> stories) {
        if (!stories.isEmpty()) {
            myStories.clear();
            myStories.addAll(stories);
            myStoriesAdapter.notifyDataSetChanged();
        }
    }
}
