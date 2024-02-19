package com.example.digii.repo

import com.example.digii.data.PostApiResponseType
import com.example.digii.data.local.PostDataDao
import com.example.digii.data.local.model.LocalPostDataEntity
import com.example.digii.data.remote.ApiPostService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class PostRepo @Inject constructor(
    private val apiPostService: ApiPostService,
    private val postDataDao: PostDataDao
) {
    private val _responseStateFlow = MutableStateFlow<PostApiResponseType>(PostApiResponseType.Loading)
    val responsePostStateFlow: StateFlow<PostApiResponseType> = _responseStateFlow

    private var _savedPostStateFlow = MutableStateFlow<List<LocalPostDataEntity>>(listOf())
    val savedPostStateFlow: StateFlow<List<LocalPostDataEntity>> = _savedPostStateFlow

    suspend fun getPostData(){
        _responseStateFlow.emit(PostApiResponseType.Loading)

        val response = apiPostService.getPosts()

        if(response.isSuccessful){
            response.body()?.let {
                _responseStateFlow.emit(PostApiResponseType.Success(it))
            } ?: run {
                _responseStateFlow.emit(PostApiResponseType.NoData)
            }
        }else{
            _responseStateFlow.emit(PostApiResponseType.Failure)
        }
    }

    suspend fun getAllSavedPost(){
        postDataDao.getAllLivePostData().collect{
            _savedPostStateFlow.emit(it)
        }
    }

    suspend fun deleteSavedPost(post: LocalPostDataEntity) {
        postDataDao.deletePost(post)
    }

    suspend fun savePost(post: LocalPostDataEntity){
        postDataDao.insertPostData(post)
    }

}