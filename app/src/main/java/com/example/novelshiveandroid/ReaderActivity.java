package com.example.novelshiveandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.novelshiveandroid.models.Chapter;
import com.example.novelshiveandroid.presenters.ReaderPresenter;
import com.example.novelshiveandroid.viewModels.ReaderViewModel;
import com.example.novelshiveandroid.views.ReaderView;

import java.util.ArrayList;

public class ReaderActivity extends AppCompatActivity implements ReaderView {

    private TextView tvChapterText;

    ReaderPresenter mReaderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        tvChapterText = findViewById(R.id.tv_chapter_text);

        mReaderPresenter = new ReaderViewModel(ReaderActivity.this);
        mReaderPresenter.getChapterInfos(1);
    }

    @Override
    public void displayChapter(Chapter chapter) {
        if(chapter != null) {
            String chapterText = mReaderPresenter.convertText((ArrayList<Double>)chapter.getText().get("data"));
            tvChapterText.setText(chapterText);
        }
    }
}
