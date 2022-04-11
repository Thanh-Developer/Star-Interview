package com.interview.star.data.remote.api

import com.interview.star.data.remote.model.Profile
import com.interview.star.data.remote.model.User
import com.interview.star.data.remote.response.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("users")
    fun getUsers(): Single<List<User>>

    @GET("users/{username}")
    fun getProfile(
        @Path("username") userName: String,
    ): Single<Profile>

    @GET("search/users")
    fun searchUser(
        @Query("q") key: String,
    ): Single<SearchResponse>
}