package com.edx.shell.android.twitterclient.images.dependency_injection;

import com.edx.shell.android.twitterclient.api.CustomTwitterApiClient;
import com.edx.shell.android.twitterclient.entities.Image;
import com.edx.shell.android.twitterclient.images.ImagesInteractor;
import com.edx.shell.android.twitterclient.images.ImagesInteractorImpl;
import com.edx.shell.android.twitterclient.images.ImagesPresenter;
import com.edx.shell.android.twitterclient.images.ImagesPresenterImpl;
import com.edx.shell.android.twitterclient.images.ImagesRepository;
import com.edx.shell.android.twitterclient.images.ImagesRepositoryImpl;
import com.edx.shell.android.twitterclient.images.adapters.ImagesAdapter;
import com.edx.shell.android.twitterclient.images.adapters.OnItemClickListener;
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
    private OnItemClickListener clickListener;

    public ImagesModule(ImagesView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    ImagesAdapter providesAdapter(List<Image> dataset, ImageLoader imageLoader, OnItemClickListener clickListener) {
        return new ImagesAdapter(dataset, imageLoader, clickListener);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener() {
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
