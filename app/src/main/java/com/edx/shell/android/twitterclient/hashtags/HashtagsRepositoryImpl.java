package com.edx.shell.android.twitterclient.hashtags;

import com.edx.shell.android.twitterclient.api.CustomTwitterApiClient;
import com.edx.shell.android.twitterclient.entities.Hashtag;
import com.edx.shell.android.twitterclient.hashtags.events.HashtagsEvent;
import com.edx.shell.android.twitterclient.libs.base.EventBus;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.HashtagEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HashtagsRepositoryImpl implements HashtagsRepository {

    // Constantes
    private static final int TWEET_COUNT = 100;

    // Servicios
    private EventBus eventBus;
    private CustomTwitterApiClient client;

    public HashtagsRepositoryImpl(EventBus eventBus, CustomTwitterApiClient client) {
        this.eventBus = eventBus;
        this.client = client;
    }

    @Override
    public void getHashtags() {
        Callback<List<Tweet>> callback = new Callback<List<Tweet>>() {

            @Override
            public void success(Result<List<Tweet>> result) {
                List<Hashtag> items = new ArrayList<>();
                for (Tweet tweet :
                        result.data) {
                    if (containsHashtags(tweet)) {
                        Hashtag tweetModel = new Hashtag();
                        tweetModel.setId(tweet.idStr);
                        tweetModel.setFavoriteCount(tweet.favoriteCount);
                        tweetModel.setTweetText(tweet.text);

                        List<String> hashtags = new ArrayList<>();
                        for (HashtagEntity hashtag : tweet.entities.hashtags) {
                            hashtags.add(hashtag.text);
                        }
                        tweetModel.setHashtags(hashtags);
                        items.add(tweetModel);
                    }
                }
                Collections.sort(items, new Comparator<Hashtag>() {
                    @Override
                    public int compare(Hashtag lhs, Hashtag rhs) {
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

    private boolean containsHashtags(Tweet tweet) {
        return tweet.entities != null
                && tweet.entities.hashtags != null
                && !tweet.entities.hashtags.isEmpty();
    }

    private void post(List<Hashtag> hashtags) {
        post(hashtags, null);
    }

    private void post(String error) {
        post(null, error);
    }

    private void post(List<Hashtag> hashtags, String error) {
        HashtagsEvent event = new HashtagsEvent();
        event.setError(error);
        event.setHashtags(hashtags);
        eventBus.post(event);
    }
}
