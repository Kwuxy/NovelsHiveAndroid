package com.example.novelshiveandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.novelshiveandroid.models.Chapter;
import com.example.novelshiveandroid.presenters.ReaderPresenter;
import com.example.novelshiveandroid.viewModels.ReaderViewModel;
import com.example.novelshiveandroid.views.ReaderView;

public class ReaderActivity extends AppCompatActivity implements ReaderView {

    ReaderPresenter mReaderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        mReaderPresenter = new ReaderViewModel(ReaderActivity.this);
    }

    @Override
    public void displayChapter(Chapter chapter) {

    }
}
