package com.example.novelshiveandroid.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.chip.Chip;
import android.support.design.chip.ChipGroup;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.novelshiveandroid.Globals;
import com.example.novelshiveandroid.R;
import com.example.novelshiveandroid.adapter.ChaptersAdapter;
import com.example.novelshiveandroid.models.Chapter;
import com.example.novelshiveandroid.models.Favorite;
import com.example.novelshiveandroid.models.Kind;
import com.example.novelshiveandroid.models.Rating;
import com.example.novelshiveandroid.models.Story;
import com.example.novelshiveandroid.models.Tag;
import com.example.novelshiveandroid.presenters.StoryPresenter;
import com.example.novelshiveandroid.viewModels.StoryViewModel;
import com.example.novelshiveandroid.views.StoryView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.novelshiveandroid.Globals.KEY_CHAPTER_ID;
import static com.example.novelshiveandroid.Globals.KEY_STORY_ID;

public class StoryDetailsActivity extends AppCompatActivity implements StoryView {

    private Toolbar myToolbar;

    private String storyTitle;

    private Boolean inFavorite;
    private int favoriteId;
    private int storyId;

    private TextView tvStoryTitle;
    private TextView tvStoryPublicationDate;
    private TextView tvStoryUpdateDate;
    private TextView tvStorySynopsis;
    private RecyclerView rvChapters;
    private ChaptersAdapter chaptersAdapter;
    private List<Chapter> chapters;
    private ProgressBar pbLoadBackDrop;
    private ProgressBar pbLoadChapters;
    private ChipGroup chipGroup;

    private ImageView storyImage;
    private MenuItem starFavorite;

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
        mStoryPresenter.getStoryTags(storyId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_story_details, menu);
        starFavorite = menu.findItem(R.id.action_add_to_favorites);

        int userId = Globals.getCurrentToken().getUserId();
        storyId = getIntent().getIntExtra(KEY_STORY_ID, 0);
        mStoryPresenter.checkIfStoryInUserFavorites(userId, storyId);
        return true;
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

        chipGroup = findViewById(R.id.tag_group);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add_to_favorites:
                int userId = Globals.getCurrentToken().getUserId();
                if (!inFavorite){
                    mStoryPresenter.addToFavorites(userId, storyId);
                    setInFavoriteValue(true);
                }
                else{
                    mStoryPresenter.removeToFavorites(favoriteId);
                    setInFavoriteValue(false);
                }
                return true;
            default:
                finish();
                return true;

        }
    }

    @Override
    protected void onRestart() {
        int userId = Globals.getCurrentToken().getUserId();
        storyId = getIntent().getIntExtra(KEY_STORY_ID, 0);
        mStoryPresenter.checkIfStoryInUserFavorites(userId, storyId);
        super.onRestart();
    }

    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        storyImage = findViewById(R.id.iv_backdrop);

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
        readerIntent.putExtra(KEY_STORY_ID, storyId);
        startActivity(readerIntent);
    }

    @Override
    public void displayStoryInfos(Story story) {
        if (story != null) {
            storyTitle = story.getTitle();
            tvStoryTitle.setText(story.getTitle());

            // Format date
            SimpleDateFormat format = new SimpleDateFormat("\nyyyy MMMM dd", Locale.ENGLISH);

            tvStoryUpdateDate.setText(getString(R.string.placeHolderUpdateDate, format.format(story.getUpdate_date())));
            tvStoryPublicationDate.setText(getString(R.string.placeHolderPublicationDate, format.format(story.getPublication_date())));
            String synopsis = Globals.convertToText((ArrayList<Double>)story.getSynopsis().get("data"));
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
        for (int i = 0; i < tags.size(); i++) {
            final String tagName = tags.get(i).getName();
            final Chip chip = new Chip(this);
            int paddingDp = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 10,
                    getResources().getDisplayMetrics()
            );
            chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp);
            chip.setText(tagName);
            //chip.setCloseIconResource(R.drawable.ic_action_navigation_close);
            //chip.setCloseIconEnabled(true);
            //Added click listener on close icon to remove tag from ChipGroup
            /*
            chip.setOnCloseIconClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tagList.remove(tagName);
                    chipGroup.removeView(chip);
                }
            });
            */
            chipGroup.addView(chip);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void displayStoryImage(Bitmap bmpImage) {
        CollapsingToolbarLayout collapsingToolbar =
                findViewById(R.id.collapsing_toolbar);
        storyImage.setMaxWidth(collapsingToolbar.getWidth());
        storyImage.setMaxHeight(collapsingToolbar.getHeight());

        int bmpWidth = bmpImage.getWidth();
        if(bmpWidth > storyImage.getMaxWidth()){
            bmpWidth = storyImage.getMaxWidth();
        }
        int bmpHeight = bmpImage.getHeight();
        if(bmpHeight > storyImage.getMaxHeight()){
            bmpHeight = storyImage.getMaxHeight();
        }

        storyImage.setImageBitmap(Bitmap.createScaledBitmap(bmpImage, bmpWidth, bmpHeight, false));

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
        Toast.makeText(getApplicationContext(), "Story Added To Favorites", Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayFavoriteDeleting() {
        Toast.makeText(getApplicationContext(), "Story Deleted To Favorites", Toast.LENGTH_LONG).show();
    }

    @Override
    public void getFavoriteId(Favorite favorite) {
        favoriteId = favorite.getId();
    }

    @Override
    public void setInFavoriteValue(boolean checkingResult) {
        if(checkingResult){
            starFavorite.setIcon(R.drawable.ic_baseline_yellow_star_24px);
        }
        else{
            starFavorite.setIcon(R.drawable.ic_baseline_star_24px);
        }
        inFavorite = checkingResult;
    }

}
