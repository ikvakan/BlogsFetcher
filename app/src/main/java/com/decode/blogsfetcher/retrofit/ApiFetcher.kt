package com.decode.blogsfetcher.retrofit


import android.util.Log
import com.decode.blogsfetcher.model.Post
import com.decode.blogsfetcher.model.ResponseModel
import com.decode.blogsfetcher.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "api_log_tag"

class ApiFetcher {
    private val tumblrApi: TumblrAPI

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .addConverterFactory((GsonConverterFactory.create()))
            .build()
        tumblrApi = retrofit.create(TumblrAPI::class.java)
    }

    fun fetchItems(identifier: String, limit: Int, onResponse: (posts: List<Post>) -> Unit) {
        val request = tumblrApi.fetchResponseItemsWithCallback(identifier, limit)
        request.enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                response.body()?.let {
                    onResponse(it.response.posts)
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                Log.d(TAG, t.message, t)
            }
        })
    }
}