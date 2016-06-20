package com.edx.shell.android.twitterclient.hashtags;

import com.edx.shell.android.twitterclient.hashtags.events.HashtagsEvent;

public interface HashtagsPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getHashtagTweets();
    void onEventMainThread(HashtagsEvent event);
}
