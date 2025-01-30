package com.kaw.core_utils

import org.joda.time.format.DateTimeFormat
import java.util.Locale

object DateUtil {
    fun formatPublishedDate(dateString: String?): String {
        if (dateString.isNullOrBlank()){
            return ""
        }
        val inputFormat = DateTimeFormat.forPattern("yyyy-MM-dd")
        val dateTime = inputFormat.parseDateTime(dateString)
        val outputFormat = DateTimeFormat.forPattern("d MMMM").withLocale(Locale("ru"))
        val formattedDate = outputFormat.print(dateTime)
        val day = dateTime.dayOfMonth
        val month = dateTime.monthOfYear
        return "Опубликовано ${getCorrectDayNumber(day)} ${getCorrectMonthName(month)}"
    }


    private fun getCorrectDayNumber(day: Int): String {
        return if (day in 11..13) {
            "${day}-e"
        } else {
            when(day % 10){
                1 -> "${day}-e"
                2,3,4 -> "${day}-е"
                else -> "${day}-й"
            }
        }
    }

    private fun getCorrectMonthName(month: Int): String {
        return when(month){
            1 -> "января"
            2 -> "февраля"
            3 -> "марта"
            4 -> "апреля"
            5 -> "мая"
            6 -> "июня"
            7 -> "июля"
            8 -> "августа"
            9 -> "сентября"
            10 -> "октября"
            11 -> "ноября"
            12 -> "декабря"
            else -> ""
        }
    }
}