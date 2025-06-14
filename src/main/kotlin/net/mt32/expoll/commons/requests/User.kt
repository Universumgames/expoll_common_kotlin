package net.mt32.expoll.commons.requests

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import net.mt32.expoll.commons.serializable.request.CreateUserRequest
import net.mt32.expoll.commons.serializable.request.EditUserRequest
import net.mt32.expoll.commons.serializable.request.search.UserSearchParameters
import net.mt32.expoll.commons.serializable.responses.CreateUserResponse
import net.mt32.expoll.commons.serializable.responses.SafeSession

private const val USER_PATH = "/user"

suspend fun ExpollApiClient.createUser(request: CreateUserRequest): Pair<HttpStatusCode, CreateUserResponse?> {
    val client = unauthorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}$USER_PATH")
        method = HttpMethod.Post
        contentType(ContentType.Application.Json)
        setBody(request)
    }
    return if (response.status.isSuccess()) {
        Pair(response.status, response.body<CreateUserResponse>())
    } else {
        Pair(response.status, null)
    }
}

suspend fun ExpollApiClient.requestPersonalizedData(): HttpStatusCode {
    val client = authorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}$USER_PATH/requestPersonalData")
        method = HttpMethod.Get
        contentType(ContentType.Application.Json)
    }
    return response.status
}

suspend fun ExpollApiClient.getSessions(): Pair<HttpStatusCode, List<SafeSession>?> {
    val client = authorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}$USER_PATH/sessions")
        method = HttpMethod.Get
        contentType(ContentType.Application.Json)
    }
    return if (response.status.isSuccess()) {
        Pair(response.status, response.body<List<SafeSession>>())
    } else {
        Pair(response.status, null)
    }
}

suspend fun ExpollApiClient.editUser(request: EditUserRequest): HttpStatusCode {
    val client = authorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}$USER_PATH")
        method = HttpMethod.Put
        contentType(ContentType.Application.Json)
        setBody(request)
    }
    return response.status
}

suspend fun ExpollApiClient.deleteUser(): HttpStatusCode {
    val client = authorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}$USER_PATH")
        method = HttpMethod.Delete
        contentType(ContentType.Application.Json)
    }
    return response.status
}

suspend fun ExpollApiClient.deleteUserConfirm(deleteConfirmationKey: String): HttpStatusCode {
    val client = authorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}$USER_PATH/deleteConfirm")
        method = HttpMethod.Delete
        contentType(ContentType.Application.Json)
        parameter("deleteConfirmationKey", deleteConfirmationKey)
    }
    return response.status
}

suspend fun ExpollApiClient.deleteUserCancel(): HttpStatusCode {
    val client = authorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}$USER_PATH/deleteCancel")
        method = HttpMethod.Delete
        contentType(ContentType.Application.Json)
    }
    return response.status
}

suspend fun ExpollApiClient.getAvailableUserSearchParameters(): UserSearchParameters{
    val client = unauthorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}$USER_PATH/availableSearch")
        method = HttpMethod.Get
        contentType(ContentType.Application.Json)
    }
    return if (response.status.isSuccess()) {
        response.body()
    } else {
        throw IllegalStateException("Failed to get available user search parameters: ${response.status}")
    }
}