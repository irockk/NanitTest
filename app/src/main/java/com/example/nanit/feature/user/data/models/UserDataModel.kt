package com.example.nanit.feature.user.data.models

import android.net.Uri
import com.example.nanit.feature.user.domain.models.UserDomainModel

data class UserDataModel(
    val name: String?,
    val birthday: Long?,
    val picture: String?
)

fun UserDataModel.toDomain() = UserDomainModel(
    name = name,
    birthday = birthday,
    picture = Uri.parse(picture)
)

fun UserDomainModel.toData() = UserDataModel(
    name = name,
    birthday = birthday,
    picture = picture.toString()
)