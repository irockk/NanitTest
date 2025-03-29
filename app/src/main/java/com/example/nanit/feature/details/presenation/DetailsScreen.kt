package com.example.nanit.feature.details.presenation

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import com.example.nanit.R
import com.example.nanit.core.toFormatedDate
import com.example.nanit.ui.components.DatePickerModal
import com.example.nanit.ui.components.PhotoPickerBottomSheet
import com.example.nanit.ui.components.ProfileImage
import com.example.nanit.ui.theme.Dimens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    uiState: DetailsState,
    updateImage: (uri: Uri) -> Unit,
    updateName: (newName: String) -> Unit,
    updateBirthday: (date: Long?) -> Unit,
    saveData: () -> Unit,
    goToBirthday: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    val currentName = remember(uiState.name) {
        mutableStateOf(
            TextFieldValue(
                text = uiState.name,
                selection = TextRange(uiState.name.length)
            )
        )
    }

    val isDatePickerShown = rememberSaveable { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    if (sheetState.isVisible) {
        PhotoPickerBottomSheet(
            sheetState = sheetState,
            onDismiss = { coroutineScope.launch { sheetState.hide() } },
            updateImage = { newUri ->
                newUri?.let { updateImage(it) }
                coroutineScope.launch { sheetState.hide() }
            }
        )
    }

    if (isDatePickerShown.value) {
        DatePickerModal(
            onDateSelected = { date ->
                updateBirthday(date)
                isDatePickerShown.value = false
            },
            onDismiss = { isDatePickerShown.value = false }
        )
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        contentPadding = PaddingValues(Dimens.screenPadding)
    ) {
        item {
            Text(text = stringResource(R.string.app_name))

            Spacer(Modifier.height(Dimens.paddingSmall))

            Button(
                onClick = saveData
            ) {
                Text(text = stringResource(R.string.details_save_button))
            }

            Spacer(Modifier.height(Dimens.paddingSmall))

            TextField(
                value = currentName.value,
                onValueChange = { updateName(it.text) },
                label = {
                    Text(stringResource(R.string.details_name))
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }
                )
            )

            Spacer(Modifier.height(Dimens.paddingSmall))

            Text(
                modifier = Modifier.clickable { isDatePickerShown.value = true },
                text = if (uiState.birthday == null) {
                    stringResource(R.string.all_birthday_picker_button)
                } else {
                    stringResource(R.string.details_birthday, uiState.birthday.toFormatedDate())
                }
            )

            Spacer(Modifier.height(Dimens.paddingSmall))

            ProfileImage(
                modifier = Modifier
                    .padding(horizontal = Dimens.paddingBig)
                    .aspectRatio(1f),
                uri = uiState.image
            )

            Spacer(Modifier.height(Dimens.paddingSmall))

            TextButton(
                onClick = { coroutineScope.launch { sheetState.show() } }
            ) {
                Text(stringResource(R.string.details_change_photo_button))
            }

            Spacer(Modifier.height(Dimens.paddingSmall))

            Button(
                onClick = goToBirthday,
                enabled = uiState.isButtonEnabled
            ) {
                Text(stringResource(R.string.details_birthday_button))
            }
        }
    }
}