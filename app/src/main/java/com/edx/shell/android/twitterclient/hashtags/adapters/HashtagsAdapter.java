package com.edx.shell.android.twitterclient.hashtags.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edx.shell.android.twitterclient.R;
import com.edx.shell.android.twitterclient.entities.Hashtag;
import com.edx.shell.android.twitterclient.images.adapters.OnItemClickListener;

import java.util.List;

import butterknife.Bind;

public class HashtagsAdapter extends RecyclerView.Adapter<HashtagsAdapter.ViewHolder> {

    private List<Hashtag> hashtags;
    private OnItemClickListener clickListener;

    public HashtagsAdapter(List<Hashtag> hashtags, OnItemClickListener clickListener) {
        this.hashtags = hashtags;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_hashtags, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return hashtags.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.txt_tweet)
        TextView txtTweet;
        @Bind(R.id.rec_hashtags)
        RecyclerView recHashtags;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
