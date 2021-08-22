package com.itsazza.mobz.util

import java.util.*

object StringUtil {
    fun beautifyCapitalized(string: String) : String {
        return string.split("_").joinToString(" ") { it.toLowerCase().capitalize() }
    }

    fun beautifyLowerCase(string: String) : String {
        return string.split("_").joinToString(" ").toLowerCase()
    }
}