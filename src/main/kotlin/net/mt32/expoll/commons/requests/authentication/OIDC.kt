package net.mt32.expoll.commons.requests.authentication

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import net.mt32.expoll.commons.requests.ExpollApiClient
import net.mt32.expoll.commons.serializable.responses.OIDCConnection
import net.mt32.expoll.commons.serializable.responses.OIDCInfo

private val AUTH_OIDC_PATH = "/auth/oidc"

suspend fun ExpollApiClient.getOIDCProviders(): List<OIDCInfo>?{
    val client = unauthorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}$AUTH_OIDC_PATH")
        method = HttpMethod.Get
        contentType(ContentType.Application.Json)
    }
    return if (response.status.isSuccess()) {
        response.body()
    } else {
        null
    }
}

suspend fun ExpollApiClient.getExistingOIDCConnections(): Pair<HttpStatusCode, List<OIDCConnection>?>{
    val client = authorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}$AUTH_OIDC_PATH/connections")
        method = HttpMethod.Get
        contentType(ContentType.Application.Json)
    }
    return Pair(response.status, if (response.status.isSuccess()) response.body<List<OIDCConnection>>() else null)
}

suspend fun ExpollApiClient.addOIDCConnectionInit(idpShort: String): Pair<HttpStatusCode, Url?> {
    val client = authorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}$AUTH_OIDC_PATH/addConnection/$idpShort")
        method = HttpMethod.Get
        contentType(ContentType.Application.Json)
        parameter("redirect", "0")
    }
    return if (response.status.isSuccess()) {
        Pair(response.status, response.body())
    } else {
        Pair(response.status, null)
    }
}

suspend fun ExpollApiClient.removeOIDCConnection(idpShort: String): HttpStatusCode {
    val client = authorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}$AUTH_OIDC_PATH/removeConnection/$idpShort")
        method = HttpMethod.Delete
        contentType(ContentType.Application.Json)
    }
    return response.status
}