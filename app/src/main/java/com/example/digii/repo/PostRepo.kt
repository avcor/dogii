package com.example.digii.repo

import com.example.digii.data.PostApiResponseType
import com.example.digii.data.remote.ApiPostService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class PostRepo @Inject constructor(
    private val apiPostService: ApiPostService
) {
    private val _responseStateFlow = MutableStateFlow<PostApiResponseType>(PostApiResponseType.Loading)
    val responsePostStateFlow: StateFlow<PostApiResponseType> = _responseStateFlow


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
}