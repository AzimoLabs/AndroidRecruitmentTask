package com.azimolabs.mobile.task.main;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.azimolabs.mobile.task.GithubAppComponent;
import com.azimolabs.mobile.task.R;
import com.azimolabs.mobile.task.base.BaseActivity;
import com.azimolabs.mobile.task.base.BasePresenter;
import com.azimolabs.mobile.task.github.ActivityComponent;
import com.azimolabs.mobile.task.model.Repository;
import com.azimolabs.mobile.task.model.User;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class UserProfileActivity extends BaseActivity {
    @Inject
    UserProfileActivityPresenter presenter;

    @BindView(R.id.pbLoadingRepos)
    ProgressBar pbLoadingRepos;
    @BindView(R.id.tvUserError)
    TextView tvUserError;
    @BindView(R.id.llUserDetails)
    LinearLayout llUserDetails;
    @BindView(R.id.tvUserName)
    TextView tvUserName;
    @BindView(R.id.tvCompany)
    TextView tvCompany;
    @BindView(R.id.tvLocation)
    TextView tvLocation;
    @BindView(R.id.tvFollowers)
    TextView tvFollowers;
    @BindView(R.id.tvFollowing)
    TextView tvFollowing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        showLoading();

        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        presenter.getUserDetails(userName);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected ActivityComponent onCreateComponent(GithubAppComponent githubAppComponent) {
        UserProfileActivityComponent component = githubAppComponent.plus(new UserProfileActivityComponent.UserProfileActivityModule(this));
        component.inject(this);
        return component;
    }

    @Override
    protected BasePresenter getBasePresenter() {
        return presenter;
    }

    public void showError(UserFieldError error) {
        tvUserError.setText(error.getErrorMessage());
        tvUserError.setVisibility(View.VISIBLE);
        llUserDetails.setVisibility(View.GONE);
        hideLoading();
    }

    public void showUserDetails(User user) {
        tvUserName.setText(user.getName());
        tvCompany.setText(user.getCompany());
        tvLocation.setText(user.getLocation());
        tvFollowers.setText(String.format(getString(R.string.followers_txt), user.getFollowers()));
        tvFollowing.setText(String.format(getString(R.string.following_txt), user.getFollowing()));
        tvUserError.setVisibility(View.GONE);
        llUserDetails.setVisibility(View.VISIBLE);
        hideLoading();
    }

    public void showUserRepoDetails(List<Repository> userRepoList) {
        hideLoading();
    }

    public void showLoading() {
        pbLoadingRepos.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        pbLoadingRepos.setVisibility(View.INVISIBLE);
    }
}