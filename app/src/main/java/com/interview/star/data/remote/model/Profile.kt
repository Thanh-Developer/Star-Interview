package com.interview.star.data.remote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Profile(
    val id: Long,
    val login: String,
    val name: String,
    val avatar_url: String,
    val html_url: String,
    val url: String,
    val public_repos: Int,
    val followers: Int,
    val following: Int
) : Parcelable