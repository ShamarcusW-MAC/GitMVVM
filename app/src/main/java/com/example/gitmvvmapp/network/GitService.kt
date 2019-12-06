package com.example.gitmvvmapp.network

import com.example.gitmvvmapp.model.Commit
import com.example.gitmvvmapp.model.Commits
import com.example.gitmvvmapp.model.Repository
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GitService {

    @GET("users/{user_name}/repos")
    fun getUser(@Path("user_name")username: String) : Observable<List<Repository>>


    @GET("repos/{user_name}/{repo_name}/commits")
    fun getRepoCommit(@Path("user_name")username: String,
            @Path("repo_name")repo_name: String) : Observable<List<Commits>>
}

// https://api.github.com/repos/{user name}/{repo name}/commits