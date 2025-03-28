package com.example.nanit.feature.birthday.presentation

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nanit.core.AgeCalculator
import com.example.nanit.core.Constants
import com.example.nanit.feature.birthday.presentation.models.AgeUnit
import com.example.nanit.feature.user.domain.UserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory

data class BirthdayState(
    val name: String = Constants.EMPTY_STRING,
    val photo: Uri? = null,
    val birthday: Long? = null,
    val ageNumber: Int? = null,
    val ageUnit: AgeUnit? = null
)

@Factory
class BirthdayViewModel(
    private val userUseCase: UserUseCase,
    private val ageCalculator: AgeCalculator
) : ViewModel() {
    private val _uiState = MutableStateFlow(BirthdayState())
    val uiState = _uiState.asStateFlow()

    init {
        setUser()
        setAge()
    }

    private fun setAge() {
        viewModelScope.launch {
            uiState
                .distinctUntilChangedBy { it.birthday }
                .map { it.birthday }
                .collectLatest { birthdayNullable ->
                    birthdayNullable?.let { birthday ->
                        _uiState.update { uiState ->
                            uiState.copy(
                                ageNumber = ageCalculator.getBabyAgeNumber(birthday),
                                ageUnit = ageCalculator.getBabyAgeUnit(birthday)
                            )
                        }
                    }
                }
        }
    }

    private fun setUser() {
        viewModelScope.launch {
            userUseCase.getUser()?.let { user ->
                _uiState.update { uiState ->
                    uiState.copy(
                        name = user.name ?: Constants.EMPTY_STRING,
                        photo = user.picture,
                        birthday = user.birthday
                    )
                }
            }
        }
    }
}