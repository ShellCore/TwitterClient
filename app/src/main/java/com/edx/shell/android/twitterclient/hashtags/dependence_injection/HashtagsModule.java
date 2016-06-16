package com.edx.shell.android.twitterclient.hashtags.dependence_injection;

import com.edx.shell.android.twitterclient.api.CustomTwitterApiClient;
import com.edx.shell.android.twitterclient.entities.Hashtag;
import com.edx.shell.android.twitterclient.hashtags.HashtagsInteractor;
import com.edx.shell.android.twitterclient.hashtags.HashtagsInteractorImpl;
import com.edx.shell.android.twitterclient.hashtags.HashtagsPresenter;
import com.edx.shell.android.twitterclient.hashtags.HashtagsPresenterImpl;
import com.edx.shell.android.twitterclient.hashtags.HashtagsRepository;
import com.edx.shell.android.twitterclient.hashtags.HashtagsRepositoryImpl;
import com.edx.shell.android.twitterclient.hashtags.adapters.HashtagsAdapter;
import com.edx.shell.android.twitterclient.hashtags.ui.HashtagsView;
import com.edx.shell.android.twitterclient.images.adapters.OnItemClickListener;
import com.edx.shell.android.twitterclient.libs.base.EventBus;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Session;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class HashtagsModule {
    private HashtagsView view;
    private OnItemClickListener clickListener;

    public HashtagsModule(HashtagsView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    HashtagsAdapter providesAdapter(List<Hashtag> dataset, OnItemClickListener clickListener) {
        return new HashtagsAdapter(dataset, clickListener);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener() {
        return clickListener;
    }

    @Provides
    @Singleton
    List<Hashtag> providesItemList() {
        return new ArrayList<>();
    }

    @Provides
    @Singleton
    HashtagsPresenter providesHashtagsPresenter(HashtagsView view, EventBus eventBus, HashtagsInteractor interactor) {
        return new HashtagsPresenterImpl(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    HashtagsView providesView() {
        return view;
    }

    @Provides
    @Singleton
    HashtagsInteractor providesHashtagsInteractor(HashtagsRepository repository) {
        return new HashtagsInteractorImpl(repository);
    }

    @Provides
    @Singleton
    HashtagsRepository providesHashtagsRepository(EventBus eventBus, CustomTwitterApiClient client) {
        return new HashtagsRepositoryImpl(eventBus, client);
    }

    @Provides
    @Singleton
    CustomTwitterApiClient providesCustomTwitterApiClient(Session session) {
        return new CustomTwitterApiClient(session);
    }

    @Provides
    @Singleton
    Session providesSession() {
        return Twitter.getSessionManager().getActiveSession();
    }
}
