package com.decode.blogsfetcher.model

data class Response(
    val blog: Blog,
    val posts: List<Post>,
    val total_posts: Int
)
