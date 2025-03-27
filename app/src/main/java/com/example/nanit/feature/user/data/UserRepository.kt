package com.example.nanit.feature.user.data

import com.example.nanit.feature.user.data.models.toData
import com.example.nanit.feature.user.data.models.toDomain
import com.example.nanit.feature.user.domain.models.UserDomainModel
import org.koin.core.annotation.Factory

@Factory
class UserRepository(private val userLocalDataSource: UserLocalDataSource) {

    suspend fun getUser(): UserDomainModel? {
        return userLocalDataSource.getUser()?.toDomain()
    }

    suspend fun setUser(user: UserDomainModel) {
        userLocalDataSource.updateUser(user.toData())
    }
}