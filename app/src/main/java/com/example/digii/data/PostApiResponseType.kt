package com.example.digii.data

import com.example.digii.data.remote.model.PostModel

sealed interface PostApiResponseType{
    data class Success(val responseData: PostModel): PostApiResponseType
    data object Failure : PostApiResponseType
    data object NoData : PostApiResponseType
    data object Loading : PostApiResponseType
}