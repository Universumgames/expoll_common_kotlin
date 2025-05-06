package net.mt32.expoll.commons.requests

import io.ktor.client.request.*
import io.ktor.http.*
import net.mt32.expoll.commons.serializable.request.VoteChange

suspend fun ExpollApiClient.vote(request: VoteChange): HttpStatusCode{
    val client = authorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}/vote")
        method = HttpMethod.Post
        contentType(io.ktor.http.ContentType.Application.Json)
        setBody(request)
    }
    return response.status
}