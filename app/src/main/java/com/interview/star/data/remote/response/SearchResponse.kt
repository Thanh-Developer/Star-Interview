package com.interview.star.data.remote.response

import com.interview.star.data.remote.model.User

data class SearchResponse(
    var total_count: Int?,

    var incomplete_results: Boolean?,

    var items: List<User>?
)