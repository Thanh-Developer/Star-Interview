package com.interview.star.data.remote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id: Long,
    val login: String,
    val avatar_url: String,
    val url: String
) : Parcelable