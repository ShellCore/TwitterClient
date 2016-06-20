package com.edx.shell.android.twitterclient.images.dependence_injection;

import com.edx.shell.android.twitterclient.dependence_injection.LibsModule;
import com.edx.shell.android.twitterclient.images.ImagesPresenter;
import com.edx.shell.android.twitterclient.images.ui.ImagesFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {LibsModule.class, ImagesModule.class})
public interface ImagesComponent {
    // Opción 1 para la inyección de dependencias
    void inject(ImagesFragment fragment);
    // Opción 2
    ImagesPresenter getPresenter();
}
