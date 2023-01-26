package org.arvarik.openai.client.internal.impl

import org.arvarik.openai.client.FineTunes
import org.arvarik.openai.client.internal.http.OpenAIHTTPClient
import org.arvarik.openai.core.api.finetunes.CancelFineTuneRequest
import org.arvarik.openai.core.api.finetunes.CancelFineTuneResponse
import org.arvarik.openai.core.api.finetunes.FineTune
import org.arvarik.openai.core.api.finetunes.CreateFineTuneRequest
import org.arvarik.openai.core.api.finetunes.CreateFineTuneResponse
import org.arvarik.openai.core.api.finetunes.ListFineTunesResponse

internal class FineTunesImpl(private val httpClient: OpenAIHTTPClient) : FineTunes {
    override suspend fun createFineTune(request: CreateFineTuneRequest): CreateFineTuneResponse {
        return httpClient.post(request, FineTunesEndpoint)
    }

    override suspend fun cancelFineTune(request: CancelFineTuneRequest): CancelFineTuneResponse {
        return httpClient.post(request, "$FineTunesEndpoint/${request.fineTuneId}/cancel")
    }

    override suspend fun listFineTunes(): ListFineTunesResponse {
        return httpClient.get(FineTunesEndpoint)
    }

    companion object {
        private const val FineTunesEndpoint = "v1/fine-tunes"
    }
}
