package com.azimolabs.mobile.task.model;

import android.util.Log;

import com.azimolabs.mobile.task.github.DaggerGithubApiComponent;
import com.azimolabs.mobile.task.github.GithubApi;
import com.azimolabs.mobile.task.github.GithubApiComponent;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailsDownloader {
    private final String TAG = UserDetailsDownloader.class.getSimpleName();

    private static UserDetailsDownloader userDetailsDownloader = null;
    private GithubApi githubApi;

    public UserDetailsDownloader() {
        GithubApiComponent gitHubAPIClientComponent = DaggerGithubApiComponent.builder().build();
        githubApi = gitHubAPIClientComponent.getGitHubApi();
    }

    public static UserDetailsDownloader getInstance() {
        if (userDetailsDownloader == null) {
            userDetailsDownloader = new UserDetailsDownloader();
        }
        return userDetailsDownloader;
    }

    public void getUserDetails(final String user, OnFinishedListener listener) {
        Call<User> call = githubApi.getUserDetails(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = null;
                if (response.isSuccessful()) {
                    user = response.body();
                }

                listener.onUserDetailsDownloaded(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                call.cancel();
                listener.onUserDetailsDownloaded(null);
            }
        });
    }

    public void getUserRepoDetails(final String user, OnFinishedListener listener) {
        Call<List<Repository>> call = githubApi.getUserRepoDetails(user);

        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                List<Repository> userRepoList = new ArrayList<>();
                if (response.isSuccessful()) {
                    userRepoList = response.body();

                    for (Repository repo : userRepoList) {
                        Log.d(TAG, ">> " + repo.getName());
                    }
                }

                listener.onUserReposDownloaded(userRepoList);
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                call.cancel();
                listener.onUserDetailsDownloaded(null);
            }
        });
    }

    public interface OnFinishedListener {
        void onUserDetailsDownloaded(User user);

        void onUserReposDownloaded(List<Repository> userRepoList);
    }
}
