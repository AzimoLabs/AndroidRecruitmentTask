package com.azimolabs.mobile.task.utils;

import android.content.Intent;
import android.widget.Toast;

import com.azimolabs.mobile.task.base.BaseActivity;
import com.azimolabs.mobile.task.main.UserProfileActivity;

import javax.inject.Inject;

public class Navigator {

    private final BaseActivity activity;

    @Inject
    public Navigator(BaseActivity activity) {
        this.activity = activity;
    }

    public void navigateToUserDetailsActivity(String userName) {
        Intent intent = new Intent(activity, UserProfileActivity.class);
        intent.putExtra("userName", userName);
        activity.startActivity(intent);
    }
}
