package com.example.novelshiveandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.novelshiveandroid.R;
import com.example.novelshiveandroid.activities.MyStoriesActivity;
import com.example.novelshiveandroid.models.Story;

import java.util.List;

import static com.example.novelshiveandroid.Globals.DATE_FORMAT;

public class MyStoriesAdapter extends RecyclerView.Adapter<MyStoriesAdapter.MyViewHolder> {

    private MyStoriesActivity myStoriesActivity;
    private List<Story> myStories;

    public MyStoriesAdapter(MyStoriesActivity myStoriesActivity, List<Story> myStories) {
        this.myStoriesActivity = myStoriesActivity;
        this.myStories = myStories;
    }

    @NonNull
    @Override
    public MyStoriesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View storyView = inflater.inflate(R.layout.item_story, viewGroup, false);

        return new MyViewHolder(storyView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyStoriesAdapter.MyViewHolder myViewHolder, final int i) {
        Story story = myStories.get(i);
        TextView tvTitle = myViewHolder.tvStoryTitle;
        tvTitle.setText(story.getTitle());

        TextView tvStoryUpdateDate = myViewHolder.tvStoryUpdateDate;
        tvStoryUpdateDate.setText("Last update : " + DATE_FORMAT.format(story.getUpdate_date()));

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myStoriesActivity.onStoryItemClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myStories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvStoryTitle;
        private TextView tvStoryUpdateDate;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tvStoryTitle = itemView.findViewById(R.id.tv_story_title);
            this.tvStoryUpdateDate = itemView.findViewById(R.id.tv_story_update_date);
        }
    }
}
