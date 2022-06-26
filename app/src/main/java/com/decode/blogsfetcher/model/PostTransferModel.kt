package com.decode.blogsfetcher.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostTransferModel(
    val blog:String,
    val type: String,
    val summary: String,
    val imgUrl: String
) : Parcelable
