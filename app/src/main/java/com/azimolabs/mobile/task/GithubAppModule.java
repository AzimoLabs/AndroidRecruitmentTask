package com.azimolabs.mobile.task;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class GithubAppModule {

    private final GithubApplication application;

    public GithubAppModule(GithubApplication application) {
        this.application = application;
    }

    @Provides
    public Context provideContext() {
        return application;
    }
}