package com.example.digii.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.digii.data.PostApiResponseType
import com.example.digii.repo.PostRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActViewModel @Inject constructor(
    private val postRepo: PostRepo
) : ViewModel() {
    val TAG = "PostViewModel"

    var postResponseStateFlow: StateFlow<PostApiResponseType> = MutableStateFlow<PostApiResponseType>(PostApiResponseType.Loading)
        get() = postRepo.responsePostStateFlow

    private val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.d(TAG, " exception in dashboard VM ${throwable} ${coroutineContext}")
    }
    init {
       viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
           postRepo.getPostData()
       }
    }

}