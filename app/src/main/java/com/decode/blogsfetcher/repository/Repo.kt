package com.decode.blogsfetcher.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.decode.blogsfetcher.dao.PostDao
import com.decode.blogsfetcher.dao.PostDaoModel
import com.decode.blogsfetcher.model.Post

import com.decode.blogsfetcher.model.ResponseModel
import com.decode.blogsfetcher.retrofit.TumblrAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repo
@Inject
constructor(private val tumblrAPI: TumblrAPI, private val postDao: PostDao) {

    suspend fun getResponseItems(identifier: String, limit: Int): Flow<List<Post>> =
        flow {
            try {
                val response = tumblrAPI.fetchResponseItems(identifier, limit)
                val posts = response.response.posts
                emit(posts)
            } catch (e: Exception) {
                Log.d("repo_error", e.message.toString())
            }
        }



}
