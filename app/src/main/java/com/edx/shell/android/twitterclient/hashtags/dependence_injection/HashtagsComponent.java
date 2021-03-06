package com.edx.shell.android.twitterclient.hashtags.dependence_injection;

import com.edx.shell.android.twitterclient.dependence_injection.LibsModule;
import com.edx.shell.android.twitterclient.hashtags.HashtagsPresenter;
import com.edx.shell.android.twitterclient.hashtags.ui.HashtagsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {LibsModule.class, HashtagsModule.class})
public interface HashtagsComponent {
    HashtagsPresenter getPresenter();
    void inject(HashtagsFragment fragment);
}
