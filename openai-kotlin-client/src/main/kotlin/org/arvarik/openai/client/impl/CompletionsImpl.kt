package org.arvarik.openai.client.impl

import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

import org.arvarik.openai.client.Completions
import org.arvarik.openai.client.http.OpenAIHTTPClient
import org.arvarik.openai.core.api.completions.CreateCompletionRequest
import org.arvarik.openai.core.api.completions.CreateCompletionResponse

internal class CompletionsImpl(private val httpClient: OpenAIHTTPClient) : Completions {

    override suspend fun createCompletion(request: CreateCompletionRequest):
            CreateCompletionResponse {

        return httpClient.request {
            it.post {
                url(path = CompletionsEndpoint)
                setBody(request)
                contentType(ContentType.Application.Json)
            }.body()
        }
    }

    companion object {
        private const val CompletionsEndpoint = "v1/completions"
    }
}
