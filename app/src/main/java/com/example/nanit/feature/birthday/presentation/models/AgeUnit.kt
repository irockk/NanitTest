package com.example.nanit.feature.birthday.presentation.models

import androidx.annotation.StringRes
import com.example.nanit.R

enum class AgeUnit(
    @StringRes val singleUnitRes: Int, @StringRes val multipleUnitsRes: Int
) {
    YEAR(
        R.string.birthday_unit_year,
        R.string.birthday_unit_years
    ),
    MONTH(R.string.birthday_unit_month, R.string.birthday_unit_months);
}

fun AgeUnit.toStringRes(ageNumber: Int?): Int? {
    return when (ageNumber) {
        null -> null
        1 -> this.singleUnitRes
        else -> this.multipleUnitsRes
    }
}