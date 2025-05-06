package net.mt32.expoll.commons.serializable.responses

import kotlinx.serialization.Serializable

@Serializable
data class OIDCConnection(
    @Deprecated("Use key instead", ReplaceWith("key")) val name: String,
    val key: String,
    val mail: String?,
    val subject: String,
    val index: Int = 0
)

@Serializable
data class OIDCInfo(
    val key: String,
    val imageURI: String,
    val iconFileName: String,
    val iconBackgroundColorHex: String,
    val textColorHex: String,
    val title: String
)