package net.mt32.expoll.commons.requests

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import net.mt32.expoll.commons.interfaces.AppleRegistrationData
import net.mt32.expoll.commons.interfaces.NotificationPreferencesSerial
import net.mt32.expoll.commons.interfaces.WebRegistrationData


private const val NOTIFICATION_PATH = "/notifications"

suspend fun ExpollApiClient.getNotificationPreferences(): Pair<HttpStatusCode, NotificationPreferencesSerial?> {
    val client = authorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}$NOTIFICATION_PATH/preferences")
        method = HttpMethod.Get
        contentType(ContentType.Application.Json)
    }
    return if (response.status.isSuccess()) {
        Pair(response.status, response.body<NotificationPreferencesSerial>())
    } else {
        Pair(response.status, null)
    }
}

suspend fun ExpollApiClient.setNotificationPreferences(request: NotificationPreferencesSerial): HttpStatusCode {
    val client = authorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}$NOTIFICATION_PATH/preferences")
        method = HttpMethod.Post
        contentType(ContentType.Application.Json)
        setBody(request)
    }
    return response.status
}

suspend fun ExpollApiClient.registerNotificationAppleDevice(deviceID: String): HttpStatusCode {
    val client = authorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}$NOTIFICATION_PATH/apple")
        method = HttpMethod.Post
        contentType(ContentType.Application.Json)
        setBody(AppleRegistrationData(deviceID))
    }
    return response.status
}

suspend fun ExpollApiClient.registerNotificationWeb(request: WebRegistrationData): HttpStatusCode {
    val client = authorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}$NOTIFICATION_PATH/web")
        method = HttpMethod.Post
        contentType(ContentType.Application.Json)
        setBody(request)
    }
    return response.status
}