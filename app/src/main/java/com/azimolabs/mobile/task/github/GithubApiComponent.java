package com.azimolabs.mobile.task.github;

import dagger.Component;

@Component(modules = GitHubApiClientModule.class)
public interface GithubApiComponent {
    GithubApi getGitHubApi();
}