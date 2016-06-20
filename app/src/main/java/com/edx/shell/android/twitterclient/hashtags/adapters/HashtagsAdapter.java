package com.edx.shell.android.twitterclient.hashtags.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edx.shell.android.twitterclient.R;
import com.edx.shell.android.twitterclient.entities.Hashtag;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HashtagsAdapter extends RecyclerView.Adapter<HashtagsAdapter.ViewHolder> {

    // Constantes
    private static final int HASHTAG_NUM_COLUMNS = 3;

    // Variables
    private List<Hashtag> dataset;
    private OnHashtagItemClickListener clickListener;

    public HashtagsAdapter(List<Hashtag> dataset, OnHashtagItemClickListener clickListener) {
        this.dataset = dataset;
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_hashtags, parent, false);
        return new ViewHolder(v, parent.getContext());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Hashtag tweet = dataset.get(position);
        holder.txtTweet.setText(tweet.getTweetText());
        holder.setOnClickListener(tweet, clickListener);
        holder.setItems(tweet.getHashtags());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void setItems(List<Hashtag> newItems) {
        dataset.addAll(newItems);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // Variables
        private View view;
        private HashtagListAdapter adapter;
        private List<String> items;

        //Componentes
        @Bind(R.id.txt_tweet)
        TextView txtTweet;
        @Bind(R.id.rec_hashtags)
        RecyclerView recHashtags;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;

            items = new ArrayList<>();
            adapter = new HashtagListAdapter(items);

            CustomGridLayoutManager layoutManager = new CustomGridLayoutManager(context, HASHTAG_NUM_COLUMNS);
            recHashtags.setLayoutManager(layoutManager);
            recHashtags.setAdapter(adapter);
        }

        public void setItems(List<String> items) {
            this.items.clear();
            this.items.addAll(items);
            adapter.notifyDataSetChanged();
        }

        public void setOnClickListener(final Hashtag hashtag, final OnHashtagItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(hashtag);
                }
            });
        }
    }
}
