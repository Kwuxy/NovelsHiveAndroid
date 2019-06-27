package com.example.novelshiveandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.novelshiveandroid.HomeFragment;
import com.example.novelshiveandroid.R;
import com.example.novelshiveandroid.models.Story;

import java.util.List;

public class HomeStoryAdapter extends RecyclerView.Adapter<HomeStoryAdapter.MyViewHolder> {

    private HomeFragment homeFragment;
    private List<Story> stories;

    public HomeStoryAdapter(HomeFragment homeFragment, List<Story> stories) {
        this.homeFragment = homeFragment;
        this.stories = stories;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View storiesView = inflater.inflate(R.layout.item_story, viewGroup, false);

        return new MyViewHolder(storiesView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        Story story = stories.get(i);
        TextView tvTitle = myViewHolder.tvStoryTile;
        tvTitle.setText(story.getTitle());

        TextView tvStoryUpdateDate = myViewHolder.tvStoryUpdateDate;
        tvStoryUpdateDate.setText(story.getUpdate_date().toString());

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeFragment.onStoryItemClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvStoryTile;
        public TextView tvStoryUpdateDate;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvStoryTile = itemView.findViewById(R.id.tv_story_title);
            tvStoryUpdateDate = itemView.findViewById(R.id.tv_story_update_date);
        }
    }
}
