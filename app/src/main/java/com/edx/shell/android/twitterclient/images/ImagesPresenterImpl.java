package com.edx.shell.android.twitterclient.images;

import com.edx.shell.android.twitterclient.images.events.ImagesEvent;
import com.edx.shell.android.twitterclient.images.ui.ImagesView;
import com.edx.shell.android.twitterclient.libs.base.EventBus;

import org.greenrobot.eventbus.Subscribe;

public class ImagesPresenterImpl implements ImagesPresenter {
    private EventBus eventBus;
    private ImagesView view;
    private ImagesInteractor interactor;

    public ImagesPresenterImpl(EventBus eventBus, ImagesView view, ImagesInteractor interactor) {
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
    public void getImageTweets() {
        if (view != null) {
            view.hideImages();
            view.showProgress();
        }
        interactor.execute();
    }

    @Override
    @Subscribe
    public void onEventMainThread(ImagesEvent event) {
        if (view != null) {
            view.showImages();
            view.hideProgress();
            String errorMsg = event.getError();
            if (errorMsg != null) {
                view.onError(errorMsg);
            } else {
                view.setContents(event.getImages());
            }
        }
    }
}
