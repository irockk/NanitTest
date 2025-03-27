package com.example.nanit.feature.user.domain.models

import android.net.Uri

data class UserDomainModel(
    val name: String?,
    val birthday: Long?,
    val picture: Uri?
)