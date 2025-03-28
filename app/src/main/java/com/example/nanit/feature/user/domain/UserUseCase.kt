package com.example.nanit.feature.user.domain

import com.example.nanit.feature.user.data.UserRepository
import com.example.nanit.feature.user.domain.models.UserDomainModel
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class UserUseCase(private val userRepository: UserRepository) {

    fun getUser(): Flow<UserDomainModel?> {
        return userRepository.getUser()
    }

    suspend fun setUser(user: UserDomainModel) {
        userRepository.setUser(user)
    }
}