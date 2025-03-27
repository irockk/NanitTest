package com.example.nanit.feature.user.domain

import com.example.nanit.feature.user.data.UserRepository
import com.example.nanit.feature.user.domain.models.UserDomainModel
import org.koin.core.annotation.Factory

@Factory
class UserUseCase(private val userRepository: UserRepository) {

    suspend fun getUser(): UserDomainModel? {
        return userRepository.getUser()
    }

    suspend fun setUser(user: UserDomainModel) {
        userRepository.setUser(user)
    }
}