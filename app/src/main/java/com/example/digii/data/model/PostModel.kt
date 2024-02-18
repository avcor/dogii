package com.example.digii.data.model

import com.google.gson.annotations.SerializedName

data class PostModel(
    @SerializedName("data") var data: ArrayList<PostDataModel> = arrayListOf(),
    @SerializedName("total") var total: Int? = null,
    @SerializedName("page") var page: Int? = null,
    @SerializedName("limit") var limit: Int? = null
)