package net.mt32.expoll.commons.helper

import java.security.MessageDigest

/**
 * Utility object for hashing functions
 */
object Hash {
    /**
     * Generate MD5 hash of a string
     * @param input String to be hashed
     * @return MD5 hash of the input string
     */
    fun md5(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        val digest = md.digest(input.toByteArray())
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }
}