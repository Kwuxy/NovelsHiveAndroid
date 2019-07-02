package com.example.novelshiveandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.novelshiveandroid.R;
import com.example.novelshiveandroid.activities.StoryDetailsActivity;
import com.example.novelshiveandroid.models.Chapter;

import java.util.List;

public class ChaptersAdapter extends RecyclerView.Adapter<ChaptersAdapter.MyViewHolder> {

    private StoryDetailsActivity storyDetailsActivity;
    private List<Chapter> chapterList;

    public ChaptersAdapter(StoryDetailsActivity storyDetailsActivity, List<Chapter> chapters) {
        this.storyDetailsActivity = storyDetailsActivity;
        chapterList = chapters;
    }

    @NonNull
    @Override
    public ChaptersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View chaptersView = inflater.inflate(R.layout.item_chapter, viewGroup, false);

        return new MyViewHolder(chaptersView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        Chapter chapter = chapterList.get(i);
        TextView tvTitle = myViewHolder.tvTitle;
        tvTitle.setText(chapter.getTitle());

        TextView tvUpdateDate = myViewHolder.tvUpdateDate;
        tvUpdateDate.setText(chapter.getUpdate_date().toString());

        TextView tvNumber = myViewHolder.tvNumber;
        tvNumber.setText(String.format("" + chapter.getNumber()));

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storyDetailsActivity.onChapterItemClick(i);
            }
        });
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
