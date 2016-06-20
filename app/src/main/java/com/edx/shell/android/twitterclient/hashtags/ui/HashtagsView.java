package com.edx.shell.android.twitterclient.hashtags.ui;

import com.edx.shell.android.twitterclient.entities.Hashtag;

import java.util.List;

public interface HashtagsView {
    void showHashtags();
    void hideHashtags();
    void showProgress();
    void hideProgress();

    void onError(String error);
    void setContent(List<Hashtag> items);
}
