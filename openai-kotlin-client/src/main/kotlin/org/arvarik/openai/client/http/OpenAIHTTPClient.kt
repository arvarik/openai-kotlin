package org.arvarik.openai.client.http

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import io.ktor.util.reflect.TypeInfo
import io.ktor.util.reflect.instanceOf
import io.ktor.util.reflect.typeInfo
import kotlinx.serialization.json.Json

import org.arvarik.openai.client.OpenAIClientConfig
import org.arvarik.openai.client.exception.OpenAIClientException
import org.arvarik.openai.client.exception.OpenAIServerException

private const val OPENAI_URL: String = "api.openai.com"

internal class OpenAIHTTPClient(config: OpenAIClientConfig) {
    private val httpClient = constructHttpClient(config)

    suspend fun <T : Any> request(info: TypeInfo, block: suspend (HttpClient) -> HttpResponse): T {
        return try {
            val response = block(httpClient)

            @Suppress("UNCHECKED_CAST")
            when {
                response.instanceOf(info.type) -> response as T
                response.status.isSuccess() -> response.body(info)
                else -> throw OpenAIServerException(response.status.value, response.bodyAsText())
            }
        } catch (ex: Exception) {
            throw OpenAIClientException(throwable = ex)
        }
    }

    suspend inline fun <reified T> request(noinline block: suspend (HttpClient) -> HttpResponse):
            T {
        return request(typeInfo<T>(), block)
    }
}

private fun constructHttpClient(config: OpenAIClientConfig): HttpClient {
    return HttpClient(CIO) {
        install(ContentNegotiation) {
            register(ContentType.Application.Json, KotlinxSerializationConverter(JsonLenient))
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.HEADERS
            filter { request ->
                request.url.host.contains("ktor.io")
            }
        }

        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens(accessToken = config.token, refreshToken = "")
                }
            }
        }

        install(HttpTimeout) {
            config.timeout?.connect?.let { connect ->
                connectTimeoutMillis = connect.toMillis()
            }

            config.timeout?.request?.let { request ->
                requestTimeoutMillis = request.toMillis()
            }

            config.timeout?.socket?.let { socket ->
                socketTimeoutMillis = socket.toMillis()
            }
        }

        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = OPENAI_URL
            }
        }
    }
}

internal val JsonLenient = Json {
    isLenient = true
    ignoreUnknownKeys = true
}