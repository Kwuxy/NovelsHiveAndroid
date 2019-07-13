package com.example.novelshiveandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.novelshiveandroid.Globals;
import com.example.novelshiveandroid.R;
import com.example.novelshiveandroid.presenters.CommentWriterPresenter;
import com.example.novelshiveandroid.viewModels.CommentWriterViewModel;
import com.example.novelshiveandroid.views.CommentWriterView;

import static com.example.novelshiveandroid.Globals.KEY_CHAPTER_ID;

public class AddChapterCommentActivity extends AppCompatActivity implements CommentWriterView {

    private Toolbar toolbar;
    private EditText etTextComment;

    private int idChapter;

    private CommentWriterPresenter commentWriterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chapter_comment);

        Intent intent = getIntent();
        idChapter = intent.getIntExtra(KEY_CHAPTER_ID, 0);

        this.configureToolbar();
        this.initUI();

        this.commentWriterPresenter = new CommentWriterViewModel(this);
    }

    private void configureToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24px);
    }

    private void initUI() {
        etTextComment = findViewById(R.id.et_comment_text);
    }

    public void submitComment(View view) {
        String commentText = etTextComment.getText().toString();
        int idUser = Globals.getInstance().getCurrentToken().getUserId();

        this.commentWriterPresenter.writeComment(commentText, idUser, idChapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void commentWritingSuccess() {
        finish();
    }
}
