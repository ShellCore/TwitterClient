package com.edx.shell.android.twitterclient.images.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edx.shell.android.twitterclient.R;
import com.edx.shell.android.twitterclient.entities.Image;
import com.edx.shell.android.twitterclient.libs.base.ImageLoader;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

    private List<Image> dataset;
    private ImageLoader imageLoader;
    private OnImageItemClickListener onImageItemClickListener;

    public ImagesAdapter(List<Image> dataset, ImageLoader imageLoader, OnImageItemClickListener onImageItemClickListener) {
        this.dataset = dataset;
        this.imageLoader = imageLoader;
        this.onImageItemClickListener = onImageItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_images, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Image imageTweet = dataset.get(position);
        holder.setOnClickListener(imageTweet, onImageItemClickListener);
        holder.txtTweet.setText(imageTweet.getTweet());
        imageLoader.load(holder.imgMedia, imageTweet.getImageUrl());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void setItems(List<Image> newItems) {
        dataset.addAll(newItems);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // Variables
        private View view;

        // Componentes
        @Bind(R.id.img_media)
        ImageView imgMedia;
        @Bind(R.id.txt_tweet)
        TextView txtTweet;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.view = itemView;
        }

        public void setOnClickListener(final Image image, final OnImageItemClickListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(image);
                }
            });
        }
    }
}
