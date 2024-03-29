package org.arvarik.openai.client.internal.http

import io.ktor.client.HttpClient
import io.ktor.client.call.body
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
import io.ktor.client.request.delete
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.content.PartData
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import io.ktor.util.reflect.TypeInfo
import io.ktor.util.reflect.instanceOf
import io.ktor.util.reflect.typeInfo
import kotlinx.serialization.json.Json
import org.arvarik.openai.client.OpenAIClientConfig
import org.arvarik.openai.client.exception.OpenAIClientException
import org.arvarik.openai.client.exception.OpenAIServerException
import org.arvarik.openai.core.api.OpenAIRequest
import org.arvarik.openai.core.api.OpenAIResponse

internal class OpenAIHTTPClient(config: OpenAIClientConfig) {
    private val httpClient = constructHttpClient(config)

    suspend inline fun <reified T : OpenAIResponse> post(
        request: OpenAIRequest?,
        endpoint: String,
    ): T {
        return this.request(typeInfo<T>()) {
            it.post {
                url(path = endpoint)
                setBody(request)
                contentType(ContentType.Application.Json)
            }.body()
        }
    }

    suspend inline fun <reified T : OpenAIResponse> get(endpoint: String): T {
        return this.request(typeInfo<T>()) {
            it.get {
                url(path = endpoint)
                contentType(ContentType.Application.Json)
            }.body()
        }
    }

    suspend inline fun <reified T : OpenAIResponse> delete(endpoint: String): T {
        return this.request(typeInfo<T>()) {
            it.delete {
                url(path = endpoint)
                contentType(ContentType.Application.Json)
            }.body()
        }
    }

    suspend inline fun <reified T : OpenAIResponse> post(
        form: List<PartData>,
        endpoint: String,
    ): T {
        return this.request(typeInfo<T>()) {
            it.submitFormWithBinaryData(formData = form, url = endpoint)
        }
    }

    private suspend fun <T : Any> request(
        info: TypeInfo,
        block: suspend (HttpClient) -> HttpResponse,
    ): T {
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

    private fun constructHttpClient(config: OpenAIClientConfig): HttpClient {
        return HttpClient {
            install(ContentNegotiation) {
                register(ContentType.Application.Json, KotlinxSerializationConverter(jsonConfiguration))
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
                    connectTimeoutMillis = connect.inWholeMilliseconds
                }

                config.timeout?.request?.let { request ->
                    requestTimeoutMillis = request.inWholeMilliseconds
                }

                config.timeout?.socket?.let { socket ->
                    socketTimeoutMillis = socket.inWholeMilliseconds
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

    companion object {
        private const val OPENAI_URL: String = "api.openai.com"

        private val jsonConfiguration =
            Json {
                isLenient = true
                ignoreUnknownKeys = true
                prettyPrint = true
            }
    }
}
