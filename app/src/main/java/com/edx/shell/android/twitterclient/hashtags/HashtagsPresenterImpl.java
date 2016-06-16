package com.edx.shell.android.twitterclient.hashtags;

import com.edx.shell.android.twitterclient.hashtags.ui.HashtagsView;
import com.edx.shell.android.twitterclient.hashtags.events.HashtagsEvent;
import com.edx.shell.android.twitterclient.libs.base.EventBus;

import org.greenrobot.eventbus.Subscribe;

public class HashtagsPresenterImpl implements HashtagsPresenter {
    private EventBus eventBus;
    private HashtagsView view;
    private HashtagsInteractor interactor;

    public HashtagsPresenterImpl(EventBus eventBus, HashtagsView view, HashtagsInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onResume() {
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void getHashtagTweets() {
        if (view != null) {
            view.hideHashtags();
            view.showProgress();
        }
        interactor.execute();
    }

    @Override
    @Subscribe
    public void onEventMainThread(HashtagsEvent event) {
        if (view != null) {
            view.showHashtags();
            view.hideProgress();
            String errorMsg = event.getError();
            if (errorMsg != null) {
                view.onError(errorMsg);
            } else {
                view.setContent(event.getHashtags());
            }
        }
    }


}
