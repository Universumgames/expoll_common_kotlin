package net.mt32.expoll.commons.requests.authentication

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import net.mt32.expoll.commons.requests.ExpollApiClient
import net.mt32.expoll.commons.serializable.request.SimpleLoginRequest

private val AUTH_SIMPLE_PATH = "/auth/simple"

suspend fun ExpollApiClient.requestLoginMail(mail: String): HttpStatusCode {
    val client = unauthorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val response = client.post {
        url("${ExpollApiClient.apiBaseUrl}$AUTH_SIMPLE_PATH")
        contentType(io.ktor.http.ContentType.Application.Json)
        setBody(SimpleLoginRequest(mail))
    }
    return response.status
}

suspend fun ExpollApiClient.loginWithOTP(otp: String): Pair<HttpStatusCode, String?> {
    val client = unauthorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val response = client.post {
        url("${ExpollApiClient.apiBaseUrl}$AUTH_SIMPLE_PATH")
        contentType(io.ktor.http.ContentType.Application.Json)
        setBody(SimpleLoginRequest(otp = otp))
    }
    return if (response.status.isSuccess()) {
        Pair(response.status, response.body())
    } else {
        Pair(response.status, null)
    }
}