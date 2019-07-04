package com.example.novelshiveandroid.activities;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.novelshiveandroid.R;
import com.example.novelshiveandroid.adapter.ChaptersAdapter;
import com.example.novelshiveandroid.models.Chapter;
import com.example.novelshiveandroid.models.Kind;
import com.example.novelshiveandroid.models.PublishedComment;
import com.example.novelshiveandroid.models.Rating;
import com.example.novelshiveandroid.models.Story;
import com.example.novelshiveandroid.models.Tag;
import com.example.novelshiveandroid.presenters.StoryPresenter;
import com.example.novelshiveandroid.viewModels.StoryViewModel;
import com.example.novelshiveandroid.views.StoryView;

import java.util.ArrayList;
import java.util.List;

import static com.example.novelshiveandroid.Globals.KEY_CHAPTER_ID;
import static com.example.novelshiveandroid.Globals.KEY_STORY_ID;

public class StoryDetailsActivity extends AppCompatActivity implements StoryView {

    private Toolbar myToolbar;

    private String storyTitle;

    private TextView tvStoryTitle;
    private TextView tvStoryPublicationDate;
    private TextView tvStoryUpdateDate;
    private TextView tvStorySynopsis;
    private RecyclerView rvChapters;
    private ChaptersAdapter chaptersAdapter;
    private List<Chapter> chapters;
    private ProgressBar pbLoadBackDrop;
    private ProgressBar pbLoadChapters;

    private StoryPresenter mStoryPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_details);

        configureToolbar();
        initUI();

        Intent intent = getIntent();
        int storyId = intent.getIntExtra(KEY_STORY_ID, 0);

        mStoryPresenter = new StoryViewModel(this);
        // Request data
        mStoryPresenter.getStoryInfos(storyId);
        mStoryPresenter.getStoryChapters(storyId);
    }

    private void configureToolbar() {
        myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initCollapsingToolbar();
    }

    private void initUI() {
        tvStoryTitle = findViewById(R.id.tv_story_title);
        tvStoryPublicationDate = findViewById(R.id.tv_publication_date);
        tvStoryUpdateDate = findViewById(R.id.tv_update_date);
        tvStorySynopsis = findViewById(R.id.tv_synopsis_overview);
        rvChapters = findViewById(R.id.rv_chapters);

        chapters = new ArrayList<>();
        chaptersAdapter = new ChaptersAdapter(this, chapters);
        rvChapters.setAdapter(chaptersAdapter);

        pbLoadBackDrop = findViewById(R.id.pb_load_backdrop);
        pbLoadChapters = findViewById(R.id.pb_chapters_loading);
        // Hide progress bar for now
        pbLoadBackDrop.setVisibility(View.GONE);
        pbLoadChapters.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");

        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(storyTitle);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    public void onChapterItemClick(int position) {
        Intent readerIntent = new Intent(this, ReaderActivity.class);
        readerIntent.putExtra(KEY_CHAPTER_ID, chapters.get(position).getId());
        startActivity(readerIntent);
    }

    @Override
    public void displayStoryInfos(Story story) {
        if (story != null) {
            storyTitle = story.getTitle();
            tvStoryTitle.setText(story.getTitle());
            tvStoryUpdateDate.setText(story.getUpdate_date().toString());
            tvStoryPublicationDate.setText(story.getPublication_date().toString());
            String synopsis = mStoryPresenter.convertData((ArrayList<Double>)story.getSynopsis().get("data"));
            tvStorySynopsis.setText(synopsis);
        }
    }

    @Override
    public void displayStoryKind(Kind kind) {

    }

    @Override
    public void displayStoryRating(Rating rating) {

    }

    @Override
    public void displayStoryTags(List<Tag> tags) {

    }

    @Override
    public void displayStoryChapters(List<Chapter> chapters) {
        if (!chapters.isEmpty()) {
            this.chapters.clear();
            this.chapters.addAll(chapters);
            chaptersAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void displayStoryChaptersCount(Integer count) {

    }

    @Override
    public void displayFavoriteAdding() {

    }
}
