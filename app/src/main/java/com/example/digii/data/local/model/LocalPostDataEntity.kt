package com.example.digii.data.local.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.digii.data.remote.model.OwnerModel
import com.example.digii.data.remote.model.PostDataModel
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "posts")
class LocalPostDataEntity(

    @PrimaryKey
    @ColumnInfo(name = "postId")
    var postId: String = "",

    @ColumnInfo(name = "image")
    var image: String? = null,

    @ColumnInfo(name = "likes")
    var likes: Int? = null,

    @ColumnInfo(name = "text")
    var text: String? = null,

    @ColumnInfo(name = "publishDate")
    var publishDate: String? = null,

    @Embedded
    var owner: LocalOwnerModel? = LocalOwnerModel()
){
    fun convertToPostDataModel(): PostDataModel {
        return PostDataModel(
            postId, image, likes, arrayListOf() , text, publishDate, owner?.convertToOwnerModel()
        )
    }
}