package com.itsazza.mobz.util

object StringUtil {
    fun bountifyCapitalized(string: String) : String {
        return string.split("_").joinToString(" ") { it.toLowerCase().capitalize() }
    }

    fun bountifyLowerCase(string: String) : String {
        return string.split("_").joinToString(" ").toLowerCase()
    }
}