package com.example.nanit.core

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long?.toFormatedDate(): String {
    return if (this == null) Constants.EMPTY_STRING
    else {
        val date = Date(this)
        val simpleDateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        simpleDateFormat.format(date)
    }
}