package com.decode.blogsfetcher.viewModels

import androidx.lifecycle.*
import com.decode.blogsfetcher.dao.PostDaoModel
import com.decode.blogsfetcher.model.Post
import com.decode.blogsfetcher.model.ResponseModel
import com.decode.blogsfetcher.repository.PostDaoRepository
import com.decode.blogsfetcher.repository.PostDataFetcher
import com.decode.blogsfetcher.repository.Repo
import com.decode.blogsfetcher.retrofit.DataState
import com.decode.blogsfetcher.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelResponse
@Inject constructor(private val repo: Repo, private val postDataFetcher: PostDataFetcher,private val postDaoRepository: PostDaoRepository) :
    ViewModel() {
    private val _dataState: MutableLiveData<List<Post>> = MutableLiveData()
    val dataState: LiveData<List<Post>>
        get() = _dataState



    private val _post: MutableLiveData<List<PostDaoModel>> = MutableLiveData()
    val post: LiveData<List<PostDaoModel>>
        get() = _post

    fun fetchItems(identifier: String, limit: Int) {
        viewModelScope.launch {
            repo.getResponseItems(identifier, limit)
                .onEach { dataState ->
                    _dataState.value = dataState
                }
                .launchIn(this)
        }
    }

    fun savePostsToDatabase(identifier: String, limit: Int) {
        viewModelScope.launch {
            postDataFetcher.savePostsToDatabase(identifier, limit)

        }
    }

    fun selectAllPosts(){
        viewModelScope.launch {
            postDaoRepository.selectAllPosts().collect {
                _post.value=it
            }
        }
    }




}