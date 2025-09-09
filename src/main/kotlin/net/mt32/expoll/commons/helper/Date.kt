package net.mt32.expoll.commons.helper

import java.text.SimpleDateFormat
import java.util.*

fun Date.toUnixTimestamp(): UnixTimestamp {
    return UnixTimestamp.fromDate(this)
}

fun timestampFromString(dbString: String): Long? {
    return if (dbString.contains(":")) {
        val df = SimpleDateFormat("yyy-MM-dd HH:mm:ss")
        try {
            val date: Date = df.parse(dbString)
            date.time
        } catch (_: Exception) {
            null
        }
    } else dbString.toLongOrNull()
}

fun getMillisToMidnight(now: Calendar): Long {
    val midnight = Calendar.getInstance()
    midnight.set(Calendar.HOUR_OF_DAY, 0)
    midnight.set(Calendar.MINUTE, 0)
    midnight.set(Calendar.SECOND, 0)
    midnight.set(Calendar.MILLISECOND, 0)
    if (midnight.before(now)) {
        midnight.add(Calendar.DAY_OF_MONTH, 1)
    }
    return midnight.timeInMillis - now.timeInMillis
}