package com.example.nanit.feature.user.data

import com.example.nanit.feature.user.data.models.toData
import com.example.nanit.feature.user.data.models.toDomain
import com.example.nanit.feature.user.domain.models.UserDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
class UserRepository(private val userLocalDataSource: UserLocalDataSource) {

    fun getUser(): Flow<UserDomainModel?> {
        return userLocalDataSource.getUser().map { it?.toDomain() }
    }

    suspend fun setUser(user: UserDomainModel) {
        userLocalDataSource.updateUser(user.toData())
    }
}