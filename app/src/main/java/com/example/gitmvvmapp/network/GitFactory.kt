package com.example.gitmvvmapp.network

import com.example.gitmvvmapp.model.Commit
import com.example.gitmvvmapp.model.Commits
import com.example.gitmvvmapp.model.Repository
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GitFactory {

    val BASE_URL = "https://api.github.com"

    var gitService: GitService

    init{
        gitService = createService(retrofitInstance())
    }

    private fun retrofitInstance(): Retrofit{
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) //convert JSON objects to POJO
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//mapping responses RxJava
            .build()
    }

    private fun createService(retrofit: Retrofit) : GitService{
        return retrofit.create(GitService::class.java)
    }

    fun getRpositories(userName: String): Observable<List<Repository>> {
        return gitService.getUser(userName)
    }

    fun getRepoCommit(userName: String, repositoryName: String): Observable<List<Commits>>{
        return gitService.getRepoCommit(userName, repositoryName)
    }
}