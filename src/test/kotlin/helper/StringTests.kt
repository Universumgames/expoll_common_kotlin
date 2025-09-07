package helper

import net.mt32.expoll.commons.helper.getHostPartFromURL
import net.mt32.expoll.commons.helper.removeNullString
import net.mt32.expoll.commons.helper.replaceEmptyWithNull
import kotlin.test.Test

class StringTests {

    @Test
    fun stringWithNullIsNull() {
        // given
        val str = "null"
        // when
        val result = str.removeNullString()
        // then
        assert(result == null)
    }

    @Test
    fun stringWithNullInDifferentCaseIsNull() {
        // given
        val str = "NuLl"
        // when
        val result = str.removeNullString()
        // then
        assert(result == null)
    }

    @Test
    fun emptyStringIsNull() {
        // given
        val str = ""
        // when
        val result = str.replaceEmptyWithNull()
        // then
        assert(result == null)
    }

    @Test
    fun blankStringIsNull() {
        // given
        val str = "   "
        // when
        val result = str.replaceEmptyWithNull()
        // then
        assert(result == null)
    }

    @Test
    fun getCorrectHostFromURL(){
        // given
        val url = "https://www.example.com/path?query=123"
        // when
        val host = url.getHostPartFromURL()
        // then
        assert(host == "example.com")
    }
}