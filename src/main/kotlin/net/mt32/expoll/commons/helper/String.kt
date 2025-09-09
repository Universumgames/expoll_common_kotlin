package net.mt32.expoll.commons.helper

import io.ktor.util.*

/**
 * Remove "null" string and return null instead
 */
fun String.removeNullString(): String? {
    if (this.equals("null", ignoreCase = true)) return null
    return this
}

/**
 * Replace empty or blank string with null
 */
fun String.replaceEmptyWithNull(): String?{
    if(this.isEmpty() || this.isBlank()) return null
    return this
}

/**
 * Extract host part from a URL string
 * @return Host part of the URL or null if not found
 */
fun String.getHostPartFromURL(): String?{
    val regex = Regex("^(?:https?:\\/\\/)?(?:[^@\\/\\n]+@)?(?:www\\.)?([^:\\/?\\n]+)")
    val matchResult = regex.find(this)
    return matchResult?.groupValues?.get(1)
}

/**
 * Constant to contain all numbers
 */
val String.Companion.Numbers: String
    get() = "0123456789"

/**
 * Constant to contain all lowercase English alphabet letters
 */
val String.Companion.LowerEnglishAlphabet: String
    get() = "abcdefghijklmnopqrstuvwxyz"

/**
 * Constant to contain all uppercase English alphabet letters
 */
val String.Companion.UpperEnglishAlphabet: String
    get() = LowerEnglishAlphabet.toUpperCasePreservingASCIIRules()