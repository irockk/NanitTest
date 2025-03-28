package com.example.nanit.core

import com.example.nanit.feature.birthday.presentation.models.AgeUnit
import org.koin.core.annotation.Factory
import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId

@Factory
class AgeCalculator {
    fun getBabyAgeNumber(birthday: Long): Int {
        val birthDate = birthday.toLocalDate()
        val today = LocalDate.now()
        val period = Period.between(birthDate, today)

        return if (period.years >= 1) period.years else period.months + (period.years * 12)
    }

    fun getBabyAgeUnit(birthday: Long): AgeUnit {
        val birthDate = birthday.toLocalDate()
        val today = LocalDate.now()
        val period = Period.between(birthDate, today)
        return if (period.years >= 1) AgeUnit.YEAR else AgeUnit.MONTH
    }

    private fun Long.toLocalDate(): LocalDate {
        return Instant.ofEpochMilli(this)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
    }
}