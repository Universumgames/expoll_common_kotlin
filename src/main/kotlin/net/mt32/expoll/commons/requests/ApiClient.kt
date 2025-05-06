package net.mt32.expoll.commons.requests

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import net.mt32.expoll.commons.helper.defaultJSON

object ExpollApiClient {
    var apiBaseUrl: String? = null
    internal var jwt: String? = null

    internal var unauthorizedClient: HttpClient? = null
        private set
    internal var authorizedClient: HttpClient? = null
        private set

    fun initUnauthorized(apiEndpoint: String) {
        if (unauthorizedClient == null) {
            throw IllegalStateException("ExpollApiClient is already initialized.")
        }
        apiBaseUrl = apiEndpoint
        unauthorizedClient = HttpClient({
            install(ContentNegotiation) {
                json(defaultJSON)
            }
        })
    }

    fun initAuthorized(apiEndpoint: String, jwt: String) {
        if (authorizedClient != null) {
            throw IllegalStateException("ExpollApiClient is already initialized.")
        }
        apiBaseUrl = apiEndpoint
        this.jwt = jwt
        authorizedClient = HttpClient({
            install(ContentNegotiation) {
                json(defaultJSON)
            }
            defaultRequest {
                header(HttpHeaders.Authorization, "Bearer $jwt")
            }
        })
    }
}

val HttpMethod.Companion.Query: HttpMethod
    get() = HttpMethod("QUERY")