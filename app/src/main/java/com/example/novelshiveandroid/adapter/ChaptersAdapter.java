package com.example.novelshiveandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.novelshiveandroid.R;
import com.example.novelshiveandroid.models.Chapter;

import java.util.List;

public class ChaptersAdapter extends RecyclerView.Adapter<ChaptersAdapter.MyViewHolder> {

    private List<Chapter> chapterList;

    public ChaptersAdapter(List<Chapter> chapters) {
        chapterList = chapters;
    }

    @NonNull
    @Override
    public ChaptersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View chaptersView = inflater.inflate(R.layout.item_chapter, viewGroup, false);

        MyViewHolder myViewHolder = new MyViewHolder(chaptersView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Chapter chapter = chapterList.get(i);
        TextView tvTitle = myViewHolder.tvTitle;
        tvTitle.setText(chapter.getTitle());

        TextView tvUpdateDate = myViewHolder.tvUpdateDate;
        tvUpdateDate.setText(chapter.getUpdate_date().toString());

        TextView tvNumber = myViewHolder.tvNumber;
        tvNumber.setText(chapter.getNumber());
    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;
        public TextView tvUpdateDate;
        public TextView tvNumber;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_chapter_title);
            tvUpdateDate = itemView.findViewById(R.id.tv_chapter_update_date);
            tvNumber = itemView.findViewById(R.id.tv_chapter_number);
        }
    }
}