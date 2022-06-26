package com.decode.blogsfetcher.dao

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    exportSchema = false,
    entities = [PostDaoModel::class]
)
abstract class BlogsFetcherDatabase : RoomDatabase() {

    abstract fun postDao() : PostDao
}