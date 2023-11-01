package org.arvarik.openai.client.internal.impl

import org.arvarik.openai.client.FineTunes
import org.arvarik.openai.client.internal.http.OpenAIHTTPClient
import org.arvarik.openai.core.api.finetunes.CreateFineTuneRequest
import org.arvarik.openai.core.api.finetunes.CreateFineTuneResponse

internal class FineTunesImpl(private val httpClient: OpenAIHTTPClient) : FineTunes {
    override suspend fun createFineTune(request: CreateFineTuneRequest): CreateFineTuneResponse {
        return httpClient.post(request, FINE_TUNES_ENDPOINT)
    }

    companion object {
        private const val FINE_TUNES_ENDPOINT = "v1/fine-tunes"
    }
}
