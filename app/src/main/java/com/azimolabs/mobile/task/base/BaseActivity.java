package com.azimolabs.mobile.task.base;

import android.support.v7.app.AppCompatActivity;

import com.azimolabs.mobile.task.GithubAppComponent;
import com.azimolabs.mobile.task.GithubApplication;
import com.azimolabs.mobile.task.github.ActivityComponent;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void setContentView(int layoutResID) {
        onCreateComponent(GithubApplication.getComponent());
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    protected abstract ActivityComponent onCreateComponent(GithubAppComponent githubAppComponent);

    protected abstract BasePresenter getBasePresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getBasePresenter().unsubscribe();
    }
}