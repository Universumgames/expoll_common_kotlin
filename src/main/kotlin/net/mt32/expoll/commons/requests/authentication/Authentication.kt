package net.mt32.expoll.commons.requests.authentication

import io.ktor.client.request.*
import io.ktor.http.*
import net.mt32.expoll.commons.requests.ExpollApiClient
import net.mt32.expoll.commons.serializable.request.LogoutRequest

private val AUTH_PATH = "/auth"

suspend fun ExpollApiClient.logout(request: LogoutRequest): HttpStatusCode {
    val client = authorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}$AUTH_PATH/logout")
        contentType(io.ktor.http.ContentType.Application.Json)
        method = HttpMethod.Delete
        setBody(request)
    }
    return response.status
}

suspend fun ExpollApiClient.logoutAll(): HttpStatusCode {
    val client = authorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}$AUTH_PATH/logoutAll")
        contentType(io.ktor.http.ContentType.Application.Json)
        method = HttpMethod.Delete
    }
    return response.status
}