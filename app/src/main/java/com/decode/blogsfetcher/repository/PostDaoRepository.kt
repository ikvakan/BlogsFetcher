package com.decode.blogsfetcher.repository

import com.decode.blogsfetcher.dao.PostDao
import com.decode.blogsfetcher.dao.PostDaoModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostDaoRepository
@Inject constructor(private val postDao: PostDao) : Repository<PostDaoModel>
{
    override  fun selectAllPosts(): Flow<List<PostDaoModel>> {
        return postDao.selectAllPosts()
    }

    override suspend fun insert(obj: PostDaoModel) {
        postDao.insert(obj)
    }

}