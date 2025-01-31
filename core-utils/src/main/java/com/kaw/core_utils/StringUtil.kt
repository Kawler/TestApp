package com.kaw.core_utils

object StringUtil {
    fun getCorrectPlural(
        number: Int,
        wordForm1: String,
        wordForm2: String,
        wordForm3: String
    ): String {
        val mod10 = number % 10
        val mod100 = number % 100
        return if (mod100 in 11..14) {
            wordForm3
        } else {
            when (mod10) {
                1 -> wordForm1
                2, 3, 4 -> wordForm2
                else -> wordForm3
            }
        }
    }
}