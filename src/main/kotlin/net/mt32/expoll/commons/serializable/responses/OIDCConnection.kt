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