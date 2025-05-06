package net.mt32.expoll.commons.serializable

import kotlinx.serialization.Serializable

@Serializable
data class ClientInfo(
    val version: String,
    val build: String? = null,
    val platform: String? = null,
)