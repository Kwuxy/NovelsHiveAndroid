package com.example.novelshiveandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.novelshiveandroid.FavoriteFragment;
import com.example.novelshiveandroid.R;
import com.example.novelshiveandroid.models.Story;

import java.util.List;

public class FavoriteStoriesAdapter extends RecyclerView.Adapter<FavoriteStoriesAdapter.MyViewHolder> {

    private FavoriteFragment favoriteFragment;
    private List<Story> storiesList;

    public FavoriteStoriesAdapter(FavoriteFragment favoriteFragment, List<Story> stories) {
        this.favoriteFragment = favoriteFragment;
        storiesList = stories;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View storiesView = inflater.inflate(R.layout.item_story, viewGroup, false);

        MyViewHolder myViewHolder = new MyViewHolder(storiesView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        Story story = storiesList.get(i);

        TextView tvTitle = myViewHolder.tvStoryTitle;
        tvTitle.setText(story.getTitle());

        TextView tvUpdateDate = myViewHolder.tvUpdateDate;
        tvUpdateDate.setText(story.getUpdate_date().toString());

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favoriteFragment.onStoryItemClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return storiesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvStoryTitle;
        public TextView tvUpdateDate;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvStoryTitle = itemView.findViewById(R.id.tv_story_title);
            tvUpdateDate = itemView.findViewById(R.id.tv_story_update_date);
        }
    }
}
