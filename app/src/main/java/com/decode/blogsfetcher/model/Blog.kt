package com.decode.blogsfetcher.model

data class Blog(
    val description: String,
    val name: String,
    val posts: Int,
    val title: String,
    val total_posts: Int,
)