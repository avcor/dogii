package com.example.digii.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digii.data.PostApiResponseType
import com.example.digii.data.local.model.LocalPostDataEntity
import com.example.digii.repo.PostRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActViewModel @Inject constructor(
    private val postRepo: PostRepo
) : ViewModel() {
    private val TAG = "PostViewModel"
    private val coroutineExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d(TAG, " exception in dashboard VM ${throwable} ${coroutineContext}")
        }

    var postResponseStateFlow: StateFlow<PostApiResponseType> =
        MutableStateFlow<PostApiResponseType>(PostApiResponseType.Loading)
        get() = postRepo.responsePostStateFlow

    var savedPostStateFlow: StateFlow<List<LocalPostDataEntity>> = MutableStateFlow(listOf())
        get() = postRepo.savedPostStateFlow

    init {
        viewModelScope.launch(coroutineExceptionHandler + Dispatchers.IO) {
            postRepo.getPostData()
        }
        viewModelScope.launch(coroutineExceptionHandler + Dispatchers.IO) {
            postRepo.getAllSavedPost()
        }
    }

    suspend fun deleteSavedPost(post: LocalPostDataEntity) {
        postRepo.deleteSavedPost(post)
    }

    suspend fun savePost(post: LocalPostDataEntity) {
        postRepo.savePost(post)
    }

}