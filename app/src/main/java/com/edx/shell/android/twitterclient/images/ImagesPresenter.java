package com.edx.shell.android.twitterclient.images;

import com.edx.shell.android.twitterclient.images.events.ImagesEvent;

public interface ImagesPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getImageTweets();
    void onEventMainThread(ImagesEvent event);
}
