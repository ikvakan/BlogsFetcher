package com.decode.blogsfetcher.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_table")
data class PostDaoModel(
    @PrimaryKey(autoGenerate = true) val _idPost : Int?,
    @ColumnInfo(name = "blogName")  val blog:String,
    @ColumnInfo(name = "blogType") val type: String,
    @ColumnInfo(name = "summary") val summary: String,
    @ColumnInfo(name = "imgUrl") val imgUrl: String?,
    )
