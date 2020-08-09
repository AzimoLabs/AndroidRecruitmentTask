package com.azimolabs.mobile.task;

import com.azimolabs.mobile.task.github.GitHubApiClientModule;
import com.azimolabs.mobile.task.main.MainActivityComponent;
import com.azimolabs.mobile.task.di.UserProfileActivityComponent;

import dagger.Component;

@Component(
        modules = {
                GithubAppModule.class,
                GitHubApiClientModule.class
        }
)
public interface GithubAppComponent {

    void inject(GithubApplication application);

    MainActivityComponent plus(MainActivityComponent.MainActivityModule mainActivityModule);

    UserProfileActivityComponent plus(UserProfileActivityComponent.UserProfileActivityModule userProfileActivityModule);
}
