package com.decode.blogsfetcher.model



data class Post(
    val blog_name: String,
    val caption: String?,
    val date: String?,
    val id: Long?,
    val post_url: String?,
    val summary: String,
    val type: String,
    val photos: List<Photo>?
)
