package com.decode.blogsfetcher.repository


import kotlinx.coroutines.flow.Flow

interface Repository<T> {

     fun selectAllPosts() :Flow<List<T>>

    suspend fun insert(obj : T)
}