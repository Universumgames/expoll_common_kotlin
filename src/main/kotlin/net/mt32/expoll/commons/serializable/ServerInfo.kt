package net.mt32.expoll.commons.serializable

import kotlinx.serialization.Serializable

@Serializable
data class CompatibleVersionDescriptor(
    val from: VersionDescriptor? = null,
    val to: VersionDescriptor? = null,
    val exact: VersionDescriptor? = null,
    val platform: String? = null
)

@Serializable
data class VersionDescriptor(
    val version: String,
    val build: Int? = null
)

@Serializable
data class ServerInfo(
    val version: String,
    val compatibleVersions: List<CompatibleVersionDescriptor>,
    val serverPort: Int,
    val frontendPort: Int,
    val loginLinkBase: String,
    val mailSender: String
)