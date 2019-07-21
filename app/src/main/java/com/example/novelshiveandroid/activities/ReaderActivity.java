package com.example.novelshiveandroid.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.novelshiveandroid.Globals;
import com.example.novelshiveandroid.R;
import com.example.novelshiveandroid.models.Favorite;
import com.example.novelshiveandroid.models.ReadingChapter;
import com.example.novelshiveandroid.presenters.ReaderPresenter;
import com.example.novelshiveandroid.viewModels.ReaderViewModel;
import com.example.novelshiveandroid.views.ReaderView;

import java.util.ArrayList;

import static com.example.novelshiveandroid.Globals.KEY_CHAPTER_ID;
import static com.example.novelshiveandroid.Globals.KEY_STORY_ID;

public class ReaderActivity extends AppCompatActivity implements ReaderView, SharedPreferences.OnSharedPreferenceChangeListener {

    private Toolbar myToolbar;
    private TextView tvChapterText;
    private NestedScrollView nestedScrollView;

    private MenuItem starFavorite;

    private Boolean inFavorite;
    private int favoriteId;
    private int storyId;
    private int userId;

    private int chapterId;
    private Double previousChapterId;
    private Double nextChapterId;

    ReaderPresenter mReaderPresenter;

    private GestureDetectorCompat mDetector;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        configureToolbar();
        initUI();

        Intent intent = getIntent();
        chapterId = intent.getIntExtra(KEY_CHAPTER_ID, 0);
        userId = Globals.getCurrentToken().getUserId();

        mReaderPresenter = new ReaderViewModel(ReaderActivity.this);
        // Request data
        mReaderPresenter.getReadingChapterInfos(chapterId, userId);

        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        setupSharedPreferences();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_reader, menu);
        starFavorite = menu.findItem(R.id.action_add_to_favorites);

        storyId = getIntent().getIntExtra(KEY_STORY_ID, 0);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private void configureToolbar() {
        myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("ChapterName");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initUI() {
        tvChapterText = findViewById(R.id.tv_chapter_text);
        nestedScrollView = findViewById(R.id.content_reader);
        nestedScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                onTouchEvent(event);
                return false;
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add_to_favorites:
                int userId = Globals.getCurrentToken().getUserId();
                if (!inFavorite){
                    mReaderPresenter.addToFavorites(userId, storyId);
                    setInFavoriteValue(true);
                }
                else{
                    mReaderPresenter.removeToFavorites(favoriteId);
                    setInFavoriteValue(false);
                }
                return true;
            case R.id.action_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_comments:
                Intent chapterCommentsIntent = new Intent(this, ChapterCommentsActivity.class);
                chapterCommentsIntent.putExtra(KEY_CHAPTER_ID, chapterId);
                startActivity(chapterCommentsIntent);
                return true;
            default:
                finish();
                return true;
        }
    }

    @Override
    public void displayReadingChapter(ReadingChapter readingChapter) {
        if(readingChapter != null){
            String chapterText = Globals.convertToText((ArrayList<Double>)readingChapter.getText().get("data"));
            tvChapterText.setText(Html.fromHtml(chapterText));
            getSupportActionBar().setTitle(readingChapter.getTitle());
            previousChapterId = readingChapter.getPreviousChapter();
            nextChapterId = readingChapter.getNextChapter();
            if(readingChapter.getFavoriteId() != null){
                favoriteId = readingChapter.getFavoriteId().intValue();
                setInFavoriteValue(true);
            }
            else {
                setInFavoriteValue(false);
            }
        }
    }

    @Override
    public void goToPreviousChapter(Double previousChapterId) {
        if(previousChapterId == null){
            Toast.makeText(getApplicationContext(), "This is the first chapter of this story !", Toast.LENGTH_LONG).show();
            return;
        }
        mReaderPresenter.getReadingChapterInfos(previousChapterId.intValue(), userId);
    }

    @Override
    public void goToNextChapter(Double nextChapterId) {
        if(nextChapterId == null){
            Toast.makeText(getApplicationContext(), "This is the last published chapter of this story !", Toast.LENGTH_LONG).show();
            return;
        }
        mReaderPresenter.getReadingChapterInfos(nextChapterId.intValue(), userId);
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


    private void setupSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        loadSizeFromPreference(sharedPreferences);
        loadFamilyFromPreferences(sharedPreferences);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.pref_text_font_size_key))) {
            this.loadSizeFromPreference(sharedPreferences);
        }
        else if (key.equals(getString(R.string.pref_text_font_family_key))) {
            this.loadFamilyFromPreferences(sharedPreferences);
        }
    }

    private void loadSizeFromPreference(SharedPreferences sharedPreferences) {
        int size = Integer.parseInt(sharedPreferences.getString(getString(R.string.pref_text_font_size_key), "14"));
        tvChapterText.setTextSize(size);
    }

    private void loadFamilyFromPreferences(SharedPreferences sharedPreferences) {
        String family = sharedPreferences.getString(getString(R.string.pref_text_font_family_key), "sans-serif");
        tvChapterText.setTypeface(Typeface.create(family, Typeface.NORMAL));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        android.support.v7.preference.PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent e) {
            Log.d(DEBUG_TAG, "onDown: " + e.toString());
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d(DEBUG_TAG, "onFling: " + e1.toString() + e2.toString());

            try {
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {
                    return false;
                }
                // right to left swipe
                if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    goToNextChapter(nextChapterId);
                }
                // left to right swipe
                else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    goToPreviousChapter(previousChapterId);
                }
            }
            catch (Exception e) {
                // nothing
            }
            return true;
        }
    }
}
