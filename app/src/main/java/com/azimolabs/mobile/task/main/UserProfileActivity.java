package com.azimolabs.mobile.task.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import com.azimolabs.mobile.task.utils.ErrorType;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class UserProfileActivity extends BaseActivity {
    @Inject
    UserProfileActivityPresenter presenter;

    @BindView(R.id.pbLoadingRepos)
    ProgressBar pbLoadingRepos;
    @BindView(R.id.tvUserDetailsError)
    TextView tvUserDetailsError;
    @BindView(R.id.tvUserRepoError)
    TextView tvUserRepoError;
    @BindView(R.id.llUserDetails)
    LinearLayout llUserDetails;
    @BindView(R.id.tvUserName)
    TextView tvUserName;
    @BindView(R.id.tvUserDetails)
    TextView tvUserDetails;
    @BindView(R.id.tvFollow)
    TextView tvFollow;
    @BindView(R.id.rvRepoList)
    RecyclerView rvRepoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected BasePresenter getBasePresenter() {
        return presenter;
    }

    public void showError(UserFieldError error) {
        if(error.getType() == ErrorType.UNKNOWN_USER) {
            tvUserDetailsError.setText(error.getErrorMessage());
            tvUserDetailsError.setVisibility(View.VISIBLE);
            llUserDetails.setVisibility(View.GONE);
            hideLoading();
        } else if(error.getType() == ErrorType.NO_REPOSITORIES) {
            tvUserRepoError.setText(error.getErrorMessage());
            tvUserRepoError.setVisibility(View.VISIBLE);
            rvRepoList.setVisibility(View.GONE);
        }
    }

    public void showUserDetails(User user) {
        tvUserName.setText(user.getName());
        tvUserDetails.setText(String.format(getString(R.string.user_details_txt), user.getCompany(), user.getLocation()));
        tvFollow.setText(String.format(getString(R.string.follow_txt), user.getFollowing(), user.getFollowers()));
        tvUserDetailsError.setVisibility(View.GONE);
        llUserDetails.setVisibility(View.VISIBLE);
        hideLoading();
    }

    public void showUserRepoDetails(List<Repository> userRepoList) {
        RepoListAdapter adapter = new RepoListAdapter(userRepoList);
        rvRepoList.setAdapter(adapter);
        rvRepoList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rvRepoList.setLayoutManager(new LinearLayoutManager(this));
        hideLoading();
    }

    public void showLoading() {
        pbLoadingRepos.setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        pbLoadingRepos.setVisibility(View.INVISIBLE);
    }


    public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.ViewHolder> {
        private List<Repository> repositoryList;

        public RepoListAdapter(List<Repository> repositoryList) {
            this.repositoryList = repositoryList;
        }

        @Override
        public RepoListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            View repoView = inflater.inflate(R.layout.item_repo_list, parent, false);

            ViewHolder viewHolder = new ViewHolder(repoView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RepoListAdapter.ViewHolder holder, int position) {
            Repository repository = repositoryList.get(position);

            TextView tvRepoName = holder.tvRepoName;
            tvRepoName.setText(repository.getName());
            TextView tvRepoDescription = holder.tvRepoDescription;
            tvRepoDescription.setText(repository.getDescription());
        }

        @Override
        public int getItemCount() {
            return repositoryList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView tvRepoName;
            public TextView tvRepoDescription;

            public ViewHolder(View itemView) {
                super(itemView);

                tvRepoName = (TextView) itemView.findViewById(R.id.tvRepoName);
                tvRepoDescription = (TextView) itemView.findViewById(R.id.tvRepoDescription);
            }
        }
    }
}