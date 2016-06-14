package com.edx.shell.android.twitterclient.entities;

public class Image {

    private static final String BASE_TWEET_URL = "https://twitter.com/null/status/";

    private String id;
    private String imageUrl;
    private String tweet;
    private int favoriteCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public String getTweetUrl() {
        return BASE_TWEET_URL + id;
    }
}
