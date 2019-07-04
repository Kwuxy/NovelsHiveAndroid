package com.example.novelshiveandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.novelshiveandroid.R;
import com.example.novelshiveandroid.models.Chapter;
import com.example.novelshiveandroid.presenters.ReaderPresenter;
import com.example.novelshiveandroid.viewModels.ReaderViewModel;
import com.example.novelshiveandroid.views.ReaderView;

import java.util.ArrayList;

import static com.example.novelshiveandroid.Globals.KEY_CHAPTER_ID;

public class ReaderActivity extends AppCompatActivity implements ReaderView {

    private Toolbar myToolbar;
    private TextView tvChapterText;

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
        mReaderPresenter.getChapterInfos(chapterId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar_reader, menu);
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
            case R.id.action_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            case R.id.action_comments:
                Intent chapterCommentsIntent = new Intent(this, ChapterCommentsActivity.class);
                chapterCommentsIntent.putExtra(KEY_CHAPTER_ID, chapterId);
                startActivity(chapterCommentsIntent);
            default:
                finish();
                return true;
        }
    }

    @Override
    public void displayChapter(Chapter chapter) {
        if(chapter != null) {
            String chapterText = mReaderPresenter.convertText((ArrayList<Double>)chapter.getText().get("data"));
            tvChapterText.setText(Html.fromHtml(chapterText));
            getSupportActionBar().setTitle(chapter.getTitle());
        }
    }
}
