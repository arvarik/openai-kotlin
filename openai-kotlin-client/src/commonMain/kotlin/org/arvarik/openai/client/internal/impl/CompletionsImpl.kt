package org.arvarik.openai.client.internal.impl

import org.arvarik.openai.client.Completions
import org.arvarik.openai.client.internal.http.OpenAIHTTPClient
import org.arvarik.openai.core.api.completions.CreateCompletionRequest
import org.arvarik.openai.core.api.completions.CreateCompletionResponse

internal class CompletionsImpl(private val httpClient: OpenAIHTTPClient) : Completions {

    override suspend fun createCompletion(request: CreateCompletionRequest): CreateCompletionResponse {
        return httpClient.post(request, CompletionsEndpoint)
    }

    companion object {
        private const val CompletionsEndpoint = "v1/completions"
    }
}
