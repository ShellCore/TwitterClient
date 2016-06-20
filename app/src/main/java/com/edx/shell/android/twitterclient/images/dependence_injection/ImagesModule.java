package com.edx.shell.android.twitterclient.images.dependence_injection;

import com.edx.shell.android.twitterclient.api.CustomTwitterApiClient;
import com.edx.shell.android.twitterclient.entities.Image;
import com.edx.shell.android.twitterclient.images.ImagesInteractor;
import com.edx.shell.android.twitterclient.images.ImagesInteractorImpl;
import com.edx.shell.android.twitterclient.images.ImagesPresenter;
import com.edx.shell.android.twitterclient.images.ImagesPresenterImpl;
import com.edx.shell.android.twitterclient.images.ImagesRepository;
import com.edx.shell.android.twitterclient.images.ImagesRepositoryImpl;
import com.edx.shell.android.twitterclient.images.adapters.ImagesAdapter;
import com.edx.shell.android.twitterclient.images.adapters.OnImageItemClickListener;
import com.edx.shell.android.twitterclient.images.ui.ImagesView;
import com.edx.shell.android.twitterclient.libs.base.EventBus;
import com.edx.shell.android.twitterclient.libs.base.ImageLoader;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Session;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ImagesModule {
    private ImagesView view;
    private OnImageItemClickListener clickListener;

    public ImagesModule(ImagesView view, OnImageItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    ImagesAdapter providesAdapter(List<Image> dataset, ImageLoader imageLoader, OnImageItemClickListener clickListener) {
        return new ImagesAdapter(dataset, imageLoader, clickListener);
    }

    @Provides
    @Singleton
    OnImageItemClickListener providesOnItemClickListener() {
        return clickListener;
    }

    @Provides
    @Singleton
    List<Image> providesItemsList() {
        return new ArrayList<>();
    }

    @Provides
    @Singleton
    ImagesPresenter providesImagesPresenter(EventBus eventBus, ImagesView view, ImagesInteractor interactor) {
        return new ImagesPresenterImpl(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    ImagesView providesImagesView() {
        return view;
    }

    @Provides
    @Singleton
    ImagesInteractor providesImagesInteractor(ImagesRepository repository) {
        return new ImagesInteractorImpl(repository);
    }

    @Provides
    @Singleton
    ImagesRepository providesImagesRepository(EventBus eventBus, CustomTwitterApiClient client) {
        return new ImagesRepositoryImpl(eventBus, client);
    }

    @Provides
    @Singleton
    CustomTwitterApiClient providesCustomTwitterApiClient(Session session) {
        return new CustomTwitterApiClient(session);
    }

    @Provides
    @Singleton
    Session providesTwitterSession() {
        return Twitter.getSessionManager().getActiveSession();
    }
}
