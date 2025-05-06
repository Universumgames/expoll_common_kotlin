package net.mt32.expoll.commons.requests

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import net.mt32.expoll.commons.serializable.ClientInfo
import net.mt32.expoll.commons.serializable.PlatformInfo
import net.mt32.expoll.commons.serializable.ServerInfo

suspend fun ExpollApiClient.isClientCompatibleWithBackend(version: ClientInfo): Boolean? {
    val client = unauthorizedClient
    if (client == null) {
        return null
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}/compatibility")
        method = HttpMethod.Post
        contentType(ContentType.Application.Json)
        setBody(version)
    }
    return response.status.isSuccess()
}

suspend fun ExpollApiClient.getServerInfo(): ServerInfo? {
    val client = unauthorizedClient
    if (client == null) {
        return null
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}/serverInfo")
        method = HttpMethod.Get
        contentType(ContentType.Application.Json)
    }
    return if (response.status.isSuccess()) {
        response.body<ServerInfo>()
    } else {
        null
    }
}

enum class ClientIdentifier(val value: String) {
    IOS("ios"),
    //ANDROID("android")
}

suspend fun ExpollApiClient.getPlatformInfo(clientIdentifier: ClientIdentifier): PlatformInfo? {
    val client = unauthorizedClient
    if (client == null) {
        return null
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}/appInfo/${clientIdentifier.value}")
        method = HttpMethod.Get
        contentType(ContentType.Application.Json)
    }
    return if (response.status.isSuccess()) {
        response.body<PlatformInfo>()
    } else {
        null
    }
}