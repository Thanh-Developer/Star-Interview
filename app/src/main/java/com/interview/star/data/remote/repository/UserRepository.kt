package com.interview.star.data.remote.repository

import com.interview.star.data.remote.model.Profile
import com.interview.star.data.remote.model.User
import com.interview.star.data.remote.response.SearchResponse
import io.reactivex.Single

interface UserRepository {
    fun getAllUsers(): Single<List<User>>

    fun getProfile(userName: String): Single<Profile>

    fun searchUser(key: String): Single<SearchResponse>
}