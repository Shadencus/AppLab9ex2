package de.hhn.labapp.persistence.crm.util

import java.time.ZonedDateTime
import java.util.TimeZone

fun utcDateTime(
    year: Int = 1970,
    month: Int = 1,
    day: Int = 1,
    hour: Int = 0,
    minute: Int = 0,
    second: Int = 0,
    nanoOfSecond: Int = 0,
): ZonedDateTime {
    val utcZone = java.time.ZoneId.of("UTC")

    return ZonedDateTime.of(
        year, month, day,
        hour, minute, second, nanoOfSecond,
        utcZone
    )!!
}

fun utcNow(): ZonedDateTime {
    return ZonedDateTime.now(java.time.ZoneId.of("UTC"))
}

fun toLocalDateString(epochSecond: Long): String {
    val zone = TimeZone.getDefault()
    val formatter = java.time.format.DateTimeFormatter
        .ofLocalizedDate(java.time.format.FormatStyle.MEDIUM)
        .withZone(zone.toZoneId())

    return formatter.format(
        java.time.Instant.ofEpochSecond(epochSecond)
    )
}
