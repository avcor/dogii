package com.example.digii

import com.example.digii.data.local.model.LocalPostDataEntity

interface DeletePost {
    fun deleteSavedPost(post: LocalPostDataEntity)
}