package com.decode.blogsfetcher.repository

import android.util.Log
import com.decode.blogsfetcher.dao.PostDao
import com.decode.blogsfetcher.dao.PostDaoModel
import com.decode.blogsfetcher.model.Post
import com.decode.blogsfetcher.retrofit.TumblrAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostDataFetcher
@Inject constructor(private val tumblrAPI: TumblrAPI, private val postDao: PostDao) {
    private val dataFetcherScope = CoroutineScope(Dispatchers.IO)

    suspend fun savePostsToDatabase(identifier: String, limit: Int) {
        try {
            val response = tumblrAPI.fetchResponseItems(identifier, limit)
            val posts = response.response.posts

            populatePosts(posts)

        } catch (e: Exception) {
            Log.d("repo_error", e.message.toString())
        }
    }

    private suspend fun populatePosts(list: List<Post>) {
            list.forEach {
                postDao.insert(
                    PostDaoModel(
                        null,
                        it.blog_name,
                        it.type,
                        it.summary,
                        it.photos?.get(0)?.original_size?.url
                    )
                )
            }
    }
}