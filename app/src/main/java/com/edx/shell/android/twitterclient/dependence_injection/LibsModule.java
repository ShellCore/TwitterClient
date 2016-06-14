package com.edx.shell.android.twitterclient.dependence_injection;

import android.support.v4.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.edx.shell.android.twitterclient.libs.GlideImageLoader;
import com.edx.shell.android.twitterclient.libs.GreenRobotEventBus;
import com.edx.shell.android.twitterclient.libs.base.EventBus;
import com.edx.shell.android.twitterclient.libs.base.ImageLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LibsModule {

    private Fragment fragment;

    public LibsModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus) {
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus() {
        return org.greenrobot.eventbus.EventBus.getDefault();
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(RequestManager requestManager) {
        return new GlideImageLoader(requestManager);
    }

    @Provides
    @Singleton
    RequestManager providesRequestManager() {
        return Glide.with(fragment);
    }

    @Provides
    @Singleton
    Fragment providesFragment() {
        return fragment;
    }
}
