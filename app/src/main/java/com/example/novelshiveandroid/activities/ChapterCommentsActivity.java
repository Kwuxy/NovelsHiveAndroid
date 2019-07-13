package com.example.novelshiveandroid.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.novelshiveandroid.R;
import com.example.novelshiveandroid.adapter.ChapterCommentsAdapter;
import com.example.novelshiveandroid.models.PublishedComment;
import com.example.novelshiveandroid.presenters.ChapterCommentsPresenter;
import com.example.novelshiveandroid.viewModels.ChapterCommentsViewModel;
import com.example.novelshiveandroid.views.ChapterCommentsView;

import java.util.ArrayList;
import java.util.List;

import static com.example.novelshiveandroid.Globals.KEY_CHAPTER_ID;

public class ChapterCommentsActivity extends AppCompatActivity implements ChapterCommentsView {

    private Toolbar myToolbar;

    private RecyclerView rvChapterComments;
    private ChapterCommentsAdapter chapterCommentsAdapter;
    private List<PublishedComment> chapterComments;

    private ChapterCommentsPresenter mChapterCommentsPresenter;

    private int chapterId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_comments);

        this.configureToolbar();
        this.initUI();

        Intent intent = getIntent();
        chapterId = intent.getIntExtra(KEY_CHAPTER_ID, 0);

        mChapterCommentsPresenter = new ChapterCommentsViewModel(this);
        // Request data
        mChapterCommentsPresenter.getChapterComments(chapterId);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mChapterCommentsPresenter.getChapterComments(chapterId);
    }

    private void configureToolbar() {
        myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Comments");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initUI() {
        rvChapterComments = findViewById(R.id.rv_chapter_comments);
        rvChapterComments.setLayoutManager(new LinearLayoutManager(this));
        chapterComments = new ArrayList<>();
        chapterCommentsAdapter = new ChapterCommentsAdapter(chapterComments);
        rvChapterComments.setAdapter(chapterCommentsAdapter);

        FloatingActionButton fab = findViewById(R.id.fab_add_comment);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Test onClick fab", Toast.LENGTH_LONG).show();
                goToAddCommentChapter();
            }
        });
    }

    private void goToAddCommentChapter() {
        Intent addCommentChapterIntent = new Intent(this, AddChapterCommentActivity.class);
        addCommentChapterIntent.putExtra(KEY_CHAPTER_ID, chapterId);
        startActivity(addCommentChapterIntent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void displayChapterComments(List<PublishedComment> chapterComments) {
        if (!chapterComments.isEmpty()) {
            this.chapterComments.clear();
            this.chapterComments.addAll(chapterComments);
            chapterCommentsAdapter.notifyDataSetChanged();
        }
    }
}