package com.edx.shell.android.twitterclient;

import android.app.Application;
import android.support.v4.app.Fragment;

import com.edx.shell.android.twitterclient.dependence_injection.LibsModule;
import com.edx.shell.android.twitterclient.images.adapters.OnItemClickListener;
import com.edx.shell.android.twitterclient.images.dependency_injection.DaggerImagesComponent;
import com.edx.shell.android.twitterclient.images.dependency_injection.ImagesComponent;
import com.edx.shell.android.twitterclient.images.dependency_injection.ImagesModule;
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

    public ImagesComponent getImagesComponent(Fragment fragment, ImagesView view, OnItemClickListener clickListener) {
        return DaggerImagesComponent.builder()
                .libsModule(new LibsModule(fragment))
                .imagesModule(new ImagesModule(view, clickListener))
                .build();
    }
}
