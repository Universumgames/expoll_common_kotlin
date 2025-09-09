package net.mt32.expoll.commons.helper

import kotlinx.coroutines.runBlocking

/**
 * Run a suspend block in a new thread
 * @param block The suspend block to run
 * @return The thread running the block
 */
fun async(block: suspend () -> Unit): Thread {
    val t = Thread {
        try {
            runBlocking {
                block()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    t.start()
    return t
}