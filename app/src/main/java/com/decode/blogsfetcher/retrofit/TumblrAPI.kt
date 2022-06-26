package com.decode.blogsfetcher.retrofit


import com.decode.blogsfetcher.model.ResponseModel
import com.decode.blogsfetcher.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TumblrAPI {
    @GET("{blogIdentifier}/posts?api_key=${Constants.API_KEY}")
    fun fetchResponseItemsWithCallback(
        @Path("blogIdentifier") identifier: String,
        @Query("limit") limit: Int
    ): Call<ResponseModel>

    @GET("{blogIdentifier}/posts?api_key=${Constants.API_KEY}")
   suspend fun fetchResponseItems(
        @Path("blogIdentifier") identifier: String,
        @Query("limit") limit: Int
    ): ResponseModel
}