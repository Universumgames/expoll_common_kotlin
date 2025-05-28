package helper

import net.mt32.expoll.commons.helper.UnixTimestamp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class UnixTimestampTests {

    @Test
    fun testDateString(){
        // given
        val date = 1748432654 // 2025-05-28T11:44:14
        //when
        val timestamp = UnixTimestamp.fromSecondsSince1970(date)
        // then
        assertEquals("2025-05-28", timestamp.toDateString("UTC"))
    }

    @Test
    fun testDateTimeString(){
        // given
        val date = 1748432654 // 2025-05-28T11:44:14
        //when
        val timestamp = UnixTimestamp.fromSecondsSince1970(date)
        // then
        assertEquals("2025-05-28 11:44", timestamp.toDateTimeString("UTC"))
    }

    @Test
    fun testIsSameDayUTC(){
        // given
        val date1 = 1748432654 // 2025-05-28T11:44:14
        val date2 = 1748457854 // 2025-05-28T20:44:14

        // when
        val timestamp1 = UnixTimestamp.fromSecondsSince1970(date1)
        val timestamp2 = UnixTimestamp.fromSecondsSince1970(date2)

        // then
        assertTrue(UnixTimestamp.isSameDay(timestamp1, timestamp2, "UTC"))
    }

    @Test
    fun testIsNotSameDayUTC(){
        // given
        val date1 = 1748432654 // 2025-05-28T11:44:14
        val date2 = 1748519054 // 2025-05-29T11:44:14

        // when
        val timestamp1 = UnixTimestamp.fromSecondsSince1970(date1)
        val timestamp2 = UnixTimestamp.fromSecondsSince1970(date2)

        // then
        assertFalse(UnixTimestamp.isSameDay(timestamp1, timestamp2, "UTC"))
    }

    @Test
    fun isSameDayCST(){
        // given
        val date1 = 1748386800 // 2025-05-27T23:00:00
        val date2 = 1748390400 // 2025-05-28T01:00:00

        // when
        val timestamp1 = UnixTimestamp.fromSecondsSince1970(date1)
        val timestamp2 = UnixTimestamp.fromSecondsSince1970(date2)

        // then
        assertTrue(UnixTimestamp.isSameDay(timestamp1, timestamp2, "CST"))
    }
}