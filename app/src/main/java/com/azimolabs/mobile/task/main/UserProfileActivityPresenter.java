package com.azimolabs.mobile.task.main;

import com.azimolabs.mobile.task.base.BasePresenter;
import com.azimolabs.mobile.task.model.Repository;
import com.azimolabs.mobile.task.model.User;
import com.azimolabs.mobile.task.model.UserDetailsDownloader;

import java.util.List;

import javax.inject.Inject;

import static com.azimolabs.mobile.task.utils.ErrorType.NO_REPOSITORIES;
import static com.azimolabs.mobile.task.utils.ErrorType.UNKNOWN_USER;

public class UserProfileActivityPresenter extends BasePresenter implements UserDetailsDownloader.OnFinishedListener {
    private final UserProfileActivity view;
    private final UserFieldErrorDisposer userFieldErrorDisposer;

    @Inject
    public UserProfileActivityPresenter(UserProfileActivity view, UserFieldErrorDisposer userFieldErrorDisposer) {
        this.view = view;
        this.userFieldErrorDisposer = userFieldErrorDisposer;
    }

    public void getUserDetails(String userName) {
        UserDetailsDownloader.getInstance().getUserDetails(userName, this);
        UserDetailsDownloader.getInstance().getUserRepoDetails(userName, this);
    }

    @Override
    public void onUserDetailsDownloaded(User user) {
        if(user != null) {
            view.showUserDetails(user);
        } else {
            view.showError(userFieldErrorDisposer.getErrorText(UNKNOWN_USER));
        }
    }

    @Override
    public void onUserReposDownloaded(List<Repository> userRepoList) {
        if(userRepoList.size() > 0) {
            view.showUserRepoDetails(userRepoList);
        } else {
            view.showError(userFieldErrorDisposer.getErrorText(NO_REPOSITORIES));
        }

    }
}
