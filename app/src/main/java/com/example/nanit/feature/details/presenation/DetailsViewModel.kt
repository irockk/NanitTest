package com.example.nanit.feature.details.presenation

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.nanit.core.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.annotation.Factory

data class DetailsState(
    val name: String = Constants.EMPTY_STRING,
    val birthday: Long? = null,
    val image: Uri? = null
) {
    val isButtonEnabled: Boolean = name.isNotBlank() && birthday != null
}

@Factory
class DetailsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(DetailsState())
    val uiState = _uiState.asStateFlow()
}
