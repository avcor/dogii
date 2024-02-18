package com.example.digii.data.model

import com.google.gson.annotations.SerializedName

data class PostDataModel(

    @SerializedName("id") var id: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("likes") var likes: Int? = null,
    @SerializedName("tags") var tags: ArrayList<String> = arrayListOf(),
    @SerializedName("text") var text: String? = null,
    @SerializedName("publishDate") var publishDate: String? = null,
    @SerializedName("owner") var owner: OwnerModel? = OwnerModel()

)