package com.decode.blogsfetcher.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
 abstract class PostDao : BaseDao<PostDaoModel> {

    @Query("SELECT * FROM post_table")
    abstract fun selectAllPosts() : Flow<List<PostDaoModel>>

}