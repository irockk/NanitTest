package com.example.nanit.feature.birthday.presentation

import androidx.lifecycle.ViewModel
import com.example.nanit.core.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.annotation.Factory

data class BirthdayState(
    val name: String = Constants.EMPTY_STRING
)

@Factory
class BirthdayViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(BirthdayState())
    val uiState = _uiState.asStateFlow()
}