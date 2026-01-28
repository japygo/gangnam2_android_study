@file:OptIn(ExperimentalTime::class)

package com.survivalcoding.gangnam2kiandroidstudy.core.util

import android.text.format.DateUtils
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.number
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.time.Clock
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

fun LocalDateTime.toRelativeTime(
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): String {
    val instant = this.toInstant(timeZone)

    return DateUtils.getRelativeTimeSpanString(
        instant.toEpochMilliseconds(),
        System.currentTimeMillis(),
        DateUtils.MINUTE_IN_MILLIS,
        DateUtils.FORMAT_ABBREV_RELATIVE,
    ).toString()
}

fun LocalDateTime.toEnglishFormat(): String {
    val monthName = when (this.month.number) {
        1 -> "January"; 2 -> "February"; 3 -> "March"
        4 -> "April"; 5 -> "May"; 6 -> "June"
        7 -> "July"; 8 -> "August"; 9 -> "September"
        10 -> "October"; 11 -> "November"; 12 -> "December"
        else -> ""
    }

    val day = this.day.toString().padStart(2, '0')
    val hour = this.hour.toString().padStart(2, '0')
    val minute = this.minute.toString().padStart(2, '0')

    return "$monthName $day, ${this.year} - $hour:$minute"
}

fun LocalDateTime.isToday(
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): Boolean {
    val today = Clock.System.todayIn(timeZone)
    return this.date == today
}

fun LocalDateTime.isYesterday(
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): Boolean {
    val today = Clock.System.todayIn(timeZone)
    val yesterday = today.minus(1, DateTimeUnit.DAY)
    return this.date == yesterday
}

fun LocalDateTime.Companion.now(
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): LocalDateTime {
    return Clock.System.now().toLocalDateTime(timeZone)
}

fun LocalDateTime.plusSeconds(
    seconds: Long,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): LocalDateTime =
    adjustTime(seconds.seconds, timeZone) { inst, dur -> inst.plus(dur) }

fun LocalDateTime.plusMinutes(
    minutes: Long,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): LocalDateTime =
    adjustTime(minutes.minutes, timeZone) { inst, dur -> inst.plus(dur) }

fun LocalDateTime.plusHours(
    hours: Long,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): LocalDateTime =
    adjustTime(hours.hours, timeZone) { inst, dur -> inst.plus(dur) }

fun LocalDateTime.plusDays(
    days: Int,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): LocalDateTime =
    adjustTime(days.days, timeZone) { inst, dur -> inst.plus(dur) }

fun LocalDateTime.minusSeconds(
    seconds: Long,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): LocalDateTime =
    adjustTime(seconds.seconds, timeZone) { inst, dur -> inst.minus(dur) }

fun LocalDateTime.minusMinutes(
    minutes: Long,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): LocalDateTime =
    adjustTime(minutes.minutes, timeZone) { inst, dur -> inst.minus(dur) }

fun LocalDateTime.minusHours(
    hours: Long,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): LocalDateTime =
    adjustTime(hours.hours, timeZone) { inst, dur -> inst.minus(dur) }

fun LocalDateTime.minusDays(
    days: Int,
    timeZone: TimeZone = TimeZone.currentSystemDefault(),
): LocalDateTime =
    adjustTime(days.days, timeZone) { inst, dur -> inst.minus(dur) }

private fun LocalDateTime.adjustTime(
    duration: Duration,
    timeZone: TimeZone,
    operation: (Instant, Duration) -> Instant,
): LocalDateTime {
    val instant = this.toInstant(timeZone)
    val adjustedInstant = operation(instant, duration)
    return adjustedInstant.toLocalDateTime(timeZone)
}
