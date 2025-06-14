package net.mt32.expoll.commons.requests

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import net.mt32.expoll.commons.serializable.responses.MailRegexRules

private const val SIMPLE_PATH = "/simple"

suspend fun ExpollApiClient.getPollTitle(pollID: String): String? {
    val client = unauthorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}$SIMPLE_PATH/poll/$pollID/title")
        method = HttpMethod.Get
        contentType(ContentType.Application.Json)
    }
    return if (response.status.isSuccess()) {
        response.body<String>()
    } else {
        null
    }
}

suspend fun ExpollApiClient.getMailRegexRules(): MailRegexRules? {
    val client = unauthorizedClient
    if (client == null) {
        throw IllegalStateException("ExpollApiClient is not initialized.")
    }
    val response = client.request {
        url("${ExpollApiClient.apiBaseUrl}$SIMPLE_PATH/mailregex")
        method = HttpMethod.Get
        contentType(ContentType.Application.Json)
    }
    return if (response.status.isSuccess()) {
        response.body<MailRegexRules>()
    } else {
        null
    }
}