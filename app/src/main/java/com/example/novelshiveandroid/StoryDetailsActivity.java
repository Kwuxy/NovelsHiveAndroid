package com.example.novelshiveandroid;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.novelshiveandroid.models.Chapter;
import com.example.novelshiveandroid.models.Comment;
import com.example.novelshiveandroid.models.Kind;
import com.example.novelshiveandroid.models.Rating;
import com.example.novelshiveandroid.models.Story;
import com.example.novelshiveandroid.models.Tag;
import com.example.novelshiveandroid.views.StoryView;

import java.util.List;

import static com.example.novelshiveandroid.Globals.KEY_STORY_ID;

public class StoryDetailsActivity extends AppCompatActivity implements StoryView {

    private Toolbar myToolbar;

    private String StoryTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_details);

        this.configureToolbar();

        Intent intent = getIntent();
        int storyId = intent.getIntExtra(KEY_STORY_ID, 0);

        // Request data
    }

    private void configureToolbar() {
        myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initCollapsingToolbar();
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
                    collapsingToolbar.setTitle(" Test");
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    @Override
    public void displayStoryInfos(Story story) {

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

    }

    @Override
    public void displayStoryChaptersCount(Integer count) {

    }

    @Override
    public void displayStoryChaptersComments(List<Comment> comments) {

    }

    @Override
    public void displayFavoriteAdding() {

    }
}
