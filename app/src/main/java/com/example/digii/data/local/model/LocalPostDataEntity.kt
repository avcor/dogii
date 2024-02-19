package com.example.digii.data.local.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.digii.data.remote.model.OwnerModel
import kotlinx.parcelize.Parcelize

@Entity(tableName = "posts")
@Parcelize
data class LocalPostDataModel(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "image")
    var image: String? = null,

    @ColumnInfo(name = "likes")
    var likes: Int? = null,

    @ColumnInfo(name = "tags")
    var tags: List<String> = emptyList(),

    @ColumnInfo(name = "text")
    var text: String? = null,

    @ColumnInfo(name = "publishDate")
    var publishDate: String? = null,

    @Embedded
    var owner: OwnerModel = OwnerModel()

) : Parcelable