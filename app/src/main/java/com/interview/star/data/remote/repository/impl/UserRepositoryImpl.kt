package com.interview.star.data.remote.repository.impl

import com.interview.star.data.remote.repository.UserRepository
import com.interview.star.data.remote.api.APIService
import com.interview.star.data.remote.model.Profile
import com.interview.star.data.remote.model.User
import com.interview.star.data.remote.response.SearchResponse
import io.reactivex.Single

class UserRepositoryImpl(private val apiService: APIService) : UserRepository {
    override fun getAllUsers(): Single<List<User>> = apiService.getUsers()

    override fun getProfile(userName: String): Single<Profile> = apiService.getProfile(userName)

    override fun searchUser(key: String): Single<SearchResponse> = apiService.searchUser(key)
}