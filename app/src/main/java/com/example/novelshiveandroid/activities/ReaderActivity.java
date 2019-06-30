package com.example.novelshiveandroid.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.novelshiveandroid.R;
import com.example.novelshiveandroid.models.Chapter;
import com.example.novelshiveandroid.presenters.ReaderPresenter;
import com.example.novelshiveandroid.viewModels.ReaderViewModel;
import com.example.novelshiveandroid.views.ReaderView;

import java.util.ArrayList;

public class ReaderActivity extends AppCompatActivity implements ReaderView {

    private Toolbar myToolbar;

    private TextView tvChapterText;

    ReaderPresenter mReaderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        tvChapterText = findViewById(R.id.tv_chapter_text);

        this.configureToolbar();

        mReaderPresenter = new ReaderViewModel(ReaderActivity.this);
        mReaderPresenter.getChapterInfos(1);
    }

    private void configureToolbar() {
        myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("ChapterName");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    @Override
    public void displayChapter(Chapter chapter) {
        if(chapter != null) {
            String chapterText = mReaderPresenter.convertText((ArrayList<Double>)chapter.getText().get("data"));
            tvChapterText.setText(chapterText);
        }
    }
}
