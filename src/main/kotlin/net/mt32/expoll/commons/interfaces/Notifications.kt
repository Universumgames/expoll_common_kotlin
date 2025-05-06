package net.mt32.expoll.commons.interfaces

import kotlinx.serialization.Serializable

@Serializable
data class NotificationPreferencesSerial(
    val voteChange: Boolean? = null,
    val voteChangeDetailed: Boolean? = null,
    val userAdded: Boolean? = null,
    val userRemoved: Boolean? = null,
    val pollDeleted: Boolean? = null,
    val pollEdited: Boolean? = null,
    val pollArchived: Boolean? = null,
    val newLogin: Boolean? = null
)

@Serializable
data class AppleRegistrationData(
    val deviceID: String
)

@Serializable
data class WebRegistrationData(
    val endpoint: String,
    val expirationTime: Long? = null,
    val keys: WebRegistrationKeys
)

@Serializable
data class WebRegistrationKeys(
    val p256dh: String,
    val auth: String
)