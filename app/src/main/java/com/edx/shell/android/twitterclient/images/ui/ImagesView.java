package com.edx.shell.android.twitterclient.images.ui;

import com.edx.shell.android.twitterclient.entities.Image;

import java.util.List;

public interface ImagesView {
    void showElements();
    void hideElements();
    void showProgress();
    void hideProgress();

    void onError(String error);
    void setContents(List<Image> items);
}
