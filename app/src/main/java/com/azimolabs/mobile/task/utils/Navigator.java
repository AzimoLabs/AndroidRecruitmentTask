package com.azimolabs.mobile.task.utils;

import android.widget.Toast;

import com.azimolabs.mobile.task.base.BaseActivity;

import javax.inject.Inject;

public class Navigator {

    private final BaseActivity activity;

    @Inject
    public Navigator(BaseActivity activity) {
        this.activity = activity;
    }

    public void navigateToUserDetailsActivity(String userName) {
        //TODO: Navigate to user details screen
        Toast.makeText(activity, "Here is the place for your solution!", Toast.LENGTH_SHORT).show();
    }
}
