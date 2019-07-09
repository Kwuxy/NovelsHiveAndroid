package com.example.novelshiveandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
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
import static com.example.novelshiveandroid.Globals.KEY_PREVIOUS_CHAPTER_ID;
import static com.example.novelshiveandroid.Globals.KEY_NEXT_CHAPTER_ID;

public class ReaderActivity extends AppCompatActivity implements ReaderView {

    private Toolbar myToolbar;
    private TextView tvChapterText;

    private Boolean inFavorite;
    private int favoriteId;
    private int storyId;

    private int chapterId;

    ReaderPresenter mReaderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        configureToolbar();
        initUI();

        Intent intent = getIntent();
        chapterId = intent.getIntExtra(KEY_CHAPTER_ID, 0);


        mReaderPresenter = new ReaderViewModel(ReaderActivity.this);
        // Request data
        mReaderPresenter.getReadingChapterInfos(chapterId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_reader, menu);
        int userId = Globals.getCurrentToken().getUserId();
        storyId = getIntent().getIntExtra(KEY_STORY_ID, 0);
        mReaderPresenter.checkIfStoryInUserFavorites(userId, storyId);
        return true;
    }

    private void configureToolbar() {
        myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("ChapterName");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initUI() {
        tvChapterText = findViewById(R.id.tv_chapter_text);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add_to_favorites:
                int userId = Globals.getCurrentToken().getUserId();
                //int storyId = getIntent().getIntExtra(KEY_STORY_ID, 0);
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
            getIntent().putExtra(KEY_PREVIOUS_CHAPTER_ID, readingChapter.getPreviousChapter());
            getIntent().putExtra(KEY_NEXT_CHAPTER_ID, readingChapter.getNextChapter());
        }
    }

    @Override
    public void goToPreviousChapter(Double previousChapterId) {
        if(previousChapterId == null){
            Toast.makeText(getApplicationContext(), "This is the first chapter of this story !", Toast.LENGTH_LONG).show();
            return;
        }
        mReaderPresenter.getReadingChapterInfos(previousChapterId.intValue());
    }

    @Override
    public void goToNextChapter(Double nextChapterId) {
        if(nextChapterId == null){
            Toast.makeText(getApplicationContext(), "This is the last published chapter of this story !", Toast.LENGTH_LONG).show();
            return;
        }
        mReaderPresenter.getReadingChapterInfos(nextChapterId.intValue());
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
            //Etoile pleine
        }
        else{
            //etoile vide
        }
        inFavorite = checkingResult;
    }
}
