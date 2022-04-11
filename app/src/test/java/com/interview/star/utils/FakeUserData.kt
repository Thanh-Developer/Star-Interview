package com.interview.star.utils

import com.interview.star.data.remote.model.Profile
import com.interview.star.data.remote.model.User
import com.interview.star.data.remote.response.SearchResponse


object FakeUserData {
    const val userNameTest = "Thanh-Developer"

    const val error = "error"

    val usersTest = listOf(
        User(
            id = 0,
            login = userNameTest,
            avatar_url = "https://avatars.githubusercontent.com/u/89647236?v=4",
            url = "https://api.github.com/users/Thanh-Developer"
        )
    )

    val searchTest = SearchResponse(
        total_count = 1,
        incomplete_results = false,
        items = usersTest
    )

    val userProfileTest = Profile(
        id = 89647236,
        login = userNameTest,
        name = userNameTest,
        avatar_url = "https://avatars.githubusercontent.com/u/89647236?v=4",
        url = "https://api.github.com/users/Thanh-Developer",
        html_url = "https://api.github.com/users/Thanh-Developer",
        public_repos = 10,
        followers = 0,
        following = 0
    )
}