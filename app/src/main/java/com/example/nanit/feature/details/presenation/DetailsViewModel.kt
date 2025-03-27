package com.example.nanit.feature.details.presenation

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nanit.core.Constants
import com.example.nanit.feature.user.domain.UserUseCase
import com.example.nanit.feature.user.domain.models.UserDomainModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory

data class DetailsState(
    val name: String = Constants.EMPTY_STRING,
    val birthday: Long? = null,
    val image: Uri? = null
) {
    val isButtonEnabled: Boolean = name.isNotBlank() && birthday != null
}

@Factory
class DetailsViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailsState())
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
                        birthday = user.birthday,
                        image = user.picture
                    )
                }
            }
        }
    }

    fun saveUser() {
        viewModelScope.launch {
            with(_uiState.value) {
                userUseCase.setUser(
                    UserDomainModel(
                        name = this.name,
                        birthday = this.birthday,
                        picture = this.image
                    )
                )
            }
        }
    }

    fun updateImage(uri: Uri) {
        _uiState.update { uiState -> uiState.copy(image = uri) }
    }

    fun updateName(newName: String) {
        _uiState.update { uiState -> uiState.copy(name = newName) }
    }

    fun updateBirthday(date: Long?) {
        _uiState.update { uiState -> uiState.copy(birthday = date) }
    }
}
