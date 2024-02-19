package com.example.digii.data.local.model

import com.example.digii.data.remote.model.OwnerModel
import com.google.gson.annotations.SerializedName

class LocalOwnerModel(
    var id: String? = null,
    var title: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var picture: String? = null
) {
    fun convertToOwnerModel(): OwnerModel {
        return OwnerModel(
            id, title, firstName, lastName, picture
        )
    }
}