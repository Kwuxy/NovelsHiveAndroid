package com.example.novelshiveandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.novelshiveandroid.fragments.HomeFragment;
import com.example.novelshiveandroid.R;
import com.example.novelshiveandroid.fragments.SearchFragment;
import com.example.novelshiveandroid.models.Story;

import java.util.List;

import static com.example.novelshiveandroid.Globals.DATE_FORMAT;

public class FragmentStoriesAdapter extends RecyclerView.Adapter<FragmentStoriesAdapter.MyViewHolder> {

    //private HomeFragment homeFragment;
    private Fragment fragment;
    private List<Story> stories;

    public FragmentStoriesAdapter(Fragment fragment, List<Story> stories) {
        this.fragment = fragment;
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
        tvStoryUpdateDate.setText("Last update : " + DATE_FORMAT.format(story.getUpdate_date()));

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragment instanceof HomeFragment) {
                    ((HomeFragment) fragment).onStoryItemClick(i);
                }
                else if (fragment instanceof SearchFragment) {
                    ((SearchFragment)fragment).onStoryItemClick(i);
                }
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
