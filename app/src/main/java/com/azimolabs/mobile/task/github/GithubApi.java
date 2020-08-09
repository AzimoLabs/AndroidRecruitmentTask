package com.azimolabs.mobile.task.github;

import com.azimolabs.mobile.task.model.Repository;
import com.azimolabs.mobile.task.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubApi {

    @GET("users/{name}")
    Call<User> getUserDetails(@Path("name") String name);

    @GET("users/{name}/repos")
    Call<List<Repository>> getUserRepoDetails(@Path("name") String name);
}
