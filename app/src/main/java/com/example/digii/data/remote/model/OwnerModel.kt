package com.example.digii.data.model

import android.os.Parcelable
import com.example.digii.capitalizeFirstWord
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OwnerModel(

    @SerializedName("id") var id: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("firstName") var firstName: String? = null,
    @SerializedName("lastName") var lastName: String? = null,
    @SerializedName("picture") var picture: String? = null

) : Parcelable{
    fun getAuthorFullName(): String {
        return "${title?.capitalizeFirstWord()} ${firstName} ${lastName}"
    }
}