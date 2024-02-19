package com.example.digii.data.model

import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import com.example.digii.militaryToDayTime
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostDataModel(

    @SerializedName("id") var id: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("likes") var likes: Int? = null,
    @SerializedName("tags") var tags: ArrayList<String> = arrayListOf(),
    @SerializedName("text") var text: String? = null,
    @SerializedName("publishDate") var publishDate: String? = null,
    @SerializedName("owner") var owner: OwnerModel? = OwnerModel()

) : Parcelable {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getMilitaryToDayDate(): String {
        return publishDate.militaryToDayTime()
    }
}