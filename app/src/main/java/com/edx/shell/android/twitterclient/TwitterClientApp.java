package com.edx.shell.android.twitterclient;

import android.app.Application;
import android.support.v4.app.Fragment;

import com.edx.shell.android.twitterclient.dependence_injection.LibsModule;
import com.edx.shell.android.twitterclient.hashtags.adapters.OnHashtagItemClickListener;
import com.edx.shell.android.twitterclient.hashtags.dependence_injection.DaggerHashtagsComponent;
import com.edx.shell.android.twitterclient.hashtags.dependence_injection.HashtagsComponent;
import com.edx.shell.android.twitterclient.hashtags.dependence_injection.HashtagsModule;
import com.edx.shell.android.twitterclient.hashtags.ui.HashtagsView;
import com.edx.shell.android.twitterclient.images.adapters.OnImageItemClickListener;
import com.edx.shell.android.twitterclient.images.dependence_injection.DaggerImagesComponent;
import com.edx.shell.android.twitterclient.images.dependence_injection.ImagesComponent;
import com.edx.shell.android.twitterclient.images.dependence_injection.ImagesModule;
import com.edx.shell.android.twitterclient.images.ui.ImagesView;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

public class TwitterClientApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initFabric();
    }

    private void initFabric() {
        // TWITTER_KEY y TWITTER_SECRET deben de estár declarados en gradle.properties
        TwitterAuthConfig authConfig = new TwitterAuthConfig(BuildConfig.TWITTER_KEY, BuildConfig.TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
    }

    public ImagesComponent getImagesComponent(Fragment fragment, ImagesView view, OnImageItemClickListener clickListener) {
        return DaggerImagesComponent.builder()
                .libsModule(new LibsModule(fragment))
                .imagesModule(new ImagesModule(view, clickListener))
                .build();
    }

    public HashtagsComponent getHashtagsComponent(HashtagsView view, OnHashtagItemClickListener clickListener) {
        return DaggerHashtagsComponent.builder()
                .libsModule(new LibsModule(null))
                .hashtagsModule(new HashtagsModule(view, clickListener))
                .build();
    }
}
