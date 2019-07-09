package com.example.novelshiveandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.novelshiveandroid.Globals;
import com.example.novelshiveandroid.R;
import com.example.novelshiveandroid.models.PublishedComment;

import java.util.ArrayList;
import java.util.List;

public class ChapterCommentsAdapter extends RecyclerView.Adapter<ChapterCommentsAdapter.MyViewHolder> {

        private List<PublishedComment> chapterComments;

        public ChapterCommentsAdapter(List<PublishedComment> chapterComments) {
            this.chapterComments = chapterComments;
        }

    @NonNull
    @Override
    public ChapterCommentsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View chapterCommentView = inflater.inflate(R.layout.item_chapter_comment, viewGroup, false);

        return new MyViewHolder(chapterCommentView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterCommentsAdapter.MyViewHolder myViewHolder, int i) {
        PublishedComment comment = chapterComments.get(i);
        TextView tvUserName = myViewHolder.tvUserName;
        tvUserName.setText(comment.getUsername());

        TextView tvTextComment = myViewHolder.tvTextComment;
        //tvTextComment.setText(comment.getText());
        tvTextComment.setText(Globals.convertToText((ArrayList<Double>)comment.getText().get("data")));
    }

    @Override
    public int getItemCount() {
        return chapterComments.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvUserName;
        public TextView tvTextComment;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tv_chapter_comment_user);
            tvTextComment = itemView.findViewById(R.id.tv_chapter_comment_text);
        }
    }
}
