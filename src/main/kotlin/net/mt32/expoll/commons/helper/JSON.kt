package net.mt32.expoll.commons.helper

import kotlinx.serialization.json.*


val defaultJSON = Json {
    ignoreUnknownKeys = true
    prettyPrint = true
    encodeDefaults = true

}

/**
 * Merge two Json objects , if duplicates exist, use values from obj2
 */
fun mergeJsonObjects(obj1: JsonObject, obj2: JsonObject?): JsonObject {
    if (obj2 == null) return obj1
    val merged = obj1.toMutableMap()
    merged.forEach { entry ->
        if (obj2.containsKey(entry.key)) {
            //println(merged[entry.key].toString())
            val isObj = merged[entry.key].toString().startsWith("{")
            val isArr = merged[entry.key].toString().startsWith("[")

            if (isObj) merged[entry.key] = mergeJsonObjects(
                entry.value.jsonObject,
                obj2[entry.key]?.jsonObject
            )
            else if (isArr)
                merged[entry.key] = obj2[entry.key]?.jsonArray ?: entry.value
            else merged[entry.key] = obj2[entry.key]?.jsonPrimitive ?: entry.value
        }
    }
    obj2.forEach { entry ->
        if (!merged.containsKey(entry.key))
            merged.put(entry.key, entry.value)
    }
    return JsonObject(merged)
}

fun JsonObject.toMap(): Map<String, *> = keys.asSequence().associateWith {
    when (val value = this[it]) {
        is JsonArray -> {
            val map = (0 until value.size).associate { Pair(it.toString(), value[it]) }
            JsonObject(map).toMap().values.toList()
        }

        is JsonObject -> value.toMap()
        JsonNull -> null
        else -> value
    }
}