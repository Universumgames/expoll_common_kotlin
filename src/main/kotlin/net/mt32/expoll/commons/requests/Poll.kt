package net.mt32.expoll.commons.requests

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import net.mt32.expoll.commons.serializable.request.BasicPollOperation
import net.mt32.expoll.commons.serializable.request.PollHideRequest
import net.mt32.expoll.commons.serializable.request.search.PollSearchParameters
import net.mt32.expoll.commons.serializable.responses.DetailedPollResponse
import net.mt32.expoll.commons.serializable.responses.PollCreatedResponse
import net.mt32.expoll.commons.serializable.responses.PollListResponse
import net.mt32.expoll.commons.tPollID

private const val POLL_PATH = "/polls"

suspend fun ExpollApiClient.getPolls(searchParameters: PollSearchParameters?): Pair<HttpStatusCode, PollListResponse?>{
    val client = authorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}$POLL_PATH")
        method = HttpMethod.Get
        contentType(ContentType.Application.Json)
        setBody(searchParameters)
    }
    return if (response.status.isSuccess()) {
        Pair(response.status, response.body<PollListResponse>())
    } else {
        Pair(response.status, null)
    }
}

suspend fun ExpollApiClient.getPoll(pollID: tPollID): Pair<HttpStatusCode, DetailedPollResponse?> {
    val client = authorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}$POLL_PATH/")
        method = HttpMethod.Query
        contentType(ContentType.Application.Json)
    }
    return if (response.status.isSuccess()) {
        Pair(response.status, response.body<DetailedPollResponse>())
    } else {
        Pair(response.status, null)
    }
}

suspend fun ExpollApiClient.createPoll(request: DetailedPollResponse): Pair<HttpStatusCode, PollCreatedResponse?> {
    val client = authorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}$POLL_PATH")
        method = HttpMethod.Post
        contentType(ContentType.Application.Json)
        setBody(request)
    }
    return if (response.status.isSuccess()) {
        Pair(response.status, response.body<PollCreatedResponse>())
    } else {
        Pair(response.status, null)
    }
}

suspend fun ExpollApiClient.editPoll(request: DetailedPollResponse): HttpStatusCode {
    val client = authorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}$POLL_PATH")
        method = HttpMethod.Put
        contentType(ContentType.Application.Json)
        setBody(request)
    }
    return response.status
}

suspend fun ExpollApiClient.leavePoll(pollID: tPollID): HttpStatusCode {
    val client = authorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val body = BasicPollOperation(pollID)
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}$POLL_PATH/leave")
        method = HttpMethod.Post
        contentType(ContentType.Application.Json)
        setBody(body)
    }
    return response.status
}

suspend fun ExpollApiClient.joinPoll(pollID: tPollID): HttpStatusCode {
    val client = authorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val body = BasicPollOperation(pollID)
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}$POLL_PATH/join")
        method = HttpMethod.Post
        contentType(ContentType.Application.Json)
        setBody(body)
    }
    return response.status
}

suspend fun ExpollApiClient.hidePoll(request: PollHideRequest): HttpStatusCode {
    val client = authorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}$POLL_PATH/hide")
        method = HttpMethod.Post
        contentType(ContentType.Application.Json)
        setBody(request)
    }
    return response.status
}

suspend fun ExpollApiClient.getAvailablePollSearchParamters(): PollSearchParameters? {
    val client = unauthorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}$POLL_PATH/availableSearch")
        method = HttpMethod.Get
        contentType(ContentType.Application.Json)
    }
    return if (response.status.isSuccess()) {
        response.body<PollSearchParameters>()
    } else {
        null
    }
}