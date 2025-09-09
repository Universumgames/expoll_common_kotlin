package helper

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import net.mt32.expoll.commons.helper.JSONHelper
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class JsonTest {

    @Test
    fun testSimpleObjectToMap(){
        // given
        val obj = JsonObject(mapOf(
            "key1" to kotlinx.serialization.json.JsonPrimitive("value1"),
            "key2" to kotlinx.serialization.json.JsonPrimitive("value2")))

        // when
        val map = obj.toMap()

        // then
        assertEquals("value1", map["key1"]?.jsonPrimitive?.content)
        assertEquals("value2", map["key2"]?.jsonPrimitive?.content)
    }

    @Test
    fun testArrayObjectToMap(){
        // given
        val obj = JsonObject(mapOf(
            "key1" to kotlinx.serialization.json.JsonArray(listOf(
                kotlinx.serialization.json.JsonPrimitive("value1"),
                kotlinx.serialization.json.JsonPrimitive("value2")
            ))
        ))

        // when
        val map = obj.toMap()

        // then
        val list = map["key1"]?.jsonArray?.toList()
        assertNotNull(list)
        assertEquals(2, list.size)
        assertEquals("value1", (list[0] as kotlinx.serialization.json.JsonPrimitive).content)
        assertEquals("value2", (list[1] as kotlinx.serialization.json.JsonPrimitive).content)
    }

    @Test
    fun testNestedObjectToMap(){
        // given
        val obj = JsonObject(mapOf(
            "key1" to JsonObject(mapOf(
                "nestedKey1" to kotlinx.serialization.json.JsonPrimitive("nestedValue1"),
                "nestedKey2" to kotlinx.serialization.json.JsonPrimitive("nestedValue2")
            ))
        ))

        // when
        val map = obj.toMap()

        // then
        val nestedMap = map["key1"]?.jsonObject
        assertNotNull(nestedMap)
        assertEquals("nestedValue1", nestedMap["nestedKey1"]?.jsonPrimitive?.content)
        assertEquals("nestedValue2", nestedMap["nestedKey2"]?.jsonPrimitive?.content)
    }

    @Test
    fun testMergeNonOverlappingJsonObjects(){
        // given
        val obj = JsonObject(mapOf("key1" to kotlinx.serialization.json.JsonPrimitive("value1")))
        val obj2 = JsonObject(mapOf("key2" to kotlinx.serialization.json.JsonPrimitive("value2")))

        // when
        val merged = JSONHelper.mergeJsonObjects(obj, obj2)

        // then
        assertEquals(2, merged.size)
        assertEquals("value1", merged["key1"]?.jsonPrimitive?.content)
        assertEquals("value2", merged["key2"]?.jsonPrimitive?.content)
    }

    @Test
    fun testMergeOverlappingJsonObjects(){
        // given
        val obj = JsonObject(mapOf(
            "key1" to kotlinx.serialization.json.JsonPrimitive("value1"),
            "key2" to kotlinx.serialization.json.JsonPrimitive("value2")
        ))
        val obj2 = JsonObject(mapOf(
            "key2" to kotlinx.serialization.json.JsonPrimitive("newValue2"),
            "key3" to kotlinx.serialization.json.JsonPrimitive("value3")
        ))

        // when
        val merged = JSONHelper.mergeJsonObjects(obj, obj2)

        // then
        assertEquals(3, merged.size)
        assertEquals("value1", merged["key1"]?.jsonPrimitive?.content)
        assertEquals("newValue2", merged["key2"]?.jsonPrimitive?.content) // value from obj2 should override
        assertEquals("value3", merged["key3"]?.jsonPrimitive?.content)
    }
}