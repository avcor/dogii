package com.example.digii

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun String?.militaryToDayTime(): String {
    val instant = Instant.parse(this)

    val zonedDateTime = instant.atZone(ZoneId.of("UTC"))
    val formatter = DateTimeFormatter.ofPattern("EEE, hh:mm a")
    return formatter.format(zonedDateTime)
}

fun String.capitalizeFirstWord(): String {
    return this.substring(0, 1).uppercase() + this.substring(1)
}