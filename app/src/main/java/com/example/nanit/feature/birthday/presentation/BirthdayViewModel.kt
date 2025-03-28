package com.example.nanit.feature.birthday.presentation

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nanit.core.Constants
import com.example.nanit.feature.user.domain.UserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory

data class BirthdayState(
    val name: String = Constants.EMPTY_STRING,
    val photo: Uri? = null
)

@Factory
class BirthdayViewModel(private val userUseCase: UserUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow(BirthdayState())
    val uiState = _uiState.asStateFlow()

    init {
        setState()
    }

    private fun setState() {
        viewModelScope.launch {
            userUseCase.getUser()?.let { user ->
                _uiState.update { uiState ->
                    uiState.copy(
                        name = user.name ?: Constants.EMPTY_STRING,
                        photo = user.picture
                    )
                }
            }
        }
    }
}