package com.edx.shell.android.twitterclient.images;

import com.edx.shell.android.twitterclient.api.CustomTwitterApiClient;
import com.edx.shell.android.twitterclient.entities.Image;
import com.edx.shell.android.twitterclient.images.events.ImagesEvent;
import com.edx.shell.android.twitterclient.libs.base.EventBus;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ImagesRepositoryImpl implements ImagesRepository {

    // Constantes
    private static final int TWEET_COUNT = 100;

    // Servicios
    private EventBus eventBus;
    private CustomTwitterApiClient client;

    public ImagesRepositoryImpl(EventBus eventBus, CustomTwitterApiClient client) {
        this.eventBus = eventBus;
        this.client = client;
    }

    @Override
    public void getImages() {
        Callback<List<Tweet>> callback = new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {
                List<Image> items = new ArrayList<>();
                for (Tweet tweet :
                        result.data) {
                    if (containsImages(tweet)) {
                        Image tweetModel = new Image();
                        tweetModel.setId(tweet.idStr);
                        tweetModel.setFavoriteCount(tweet.favoriteCount);

                        String tweetText = tweet.text;
                        int index = tweetText.indexOf("http");
                        if (index > 0) {
                            tweetText = tweetText.substring(0, index);
                        }
                        tweetModel.setTweet(tweetText);

                        MediaEntity currentPhoto = tweet.entities.media.get(0);
                        String imageUrl = currentPhoto.mediaUrl;
                        tweetModel.setImageUrl(imageUrl);

                        items.add(tweetModel);
                    }
                }

                Collections.sort(items, new Comparator<Image>() {
                    @Override
                    public int compare(Image lhs, Image rhs) {
                        return rhs.getFavoriteCount() - lhs.getFavoriteCount();
                    }
                });
                post(items);
            }

            @Override
            public void failure(TwitterException e) {
                post(e.getLocalizedMessage());
            }
        };
        client.getTimelineService().homeTimeline(TWEET_COUNT, true, true, true, true, callback);
    }

    private boolean containsImages(Tweet tweet) {
        return tweet.entities != null
                && tweet.entities.media != null
                && !tweet.entities.media.isEmpty();
    }

    private void post(List<Image> images) {
        post(images, null);
    }

    private void post(String error) {
        post(null, error);
    }

    private void post(List<Image> images, String error) {
        ImagesEvent event = new ImagesEvent();
        event.setError(error);
        event.setImages(images);
        eventBus.post(event);
    }
}
