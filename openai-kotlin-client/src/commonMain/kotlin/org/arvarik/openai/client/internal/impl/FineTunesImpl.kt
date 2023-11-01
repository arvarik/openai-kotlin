package org.arvarik.openai.client.internal.impl

import org.arvarik.openai.client.FineTunes
import org.arvarik.openai.client.internal.http.OpenAIHTTPClient
import org.arvarik.openai.core.api.finetunes.CancelFineTuneRequest
import org.arvarik.openai.core.api.finetunes.CancelFineTuneResponse
import org.arvarik.openai.core.api.finetunes.CreateFineTuneRequest
import org.arvarik.openai.core.api.finetunes.CreateFineTuneResponse
import org.arvarik.openai.core.api.finetunes.DeleteFineTuneRequest
import org.arvarik.openai.core.api.finetunes.DeleteFineTuneResponse
import org.arvarik.openai.core.api.finetunes.ListFineTunesResponse

internal class FineTunesImpl(private val httpClient: OpenAIHTTPClient) : FineTunes {
    override suspend fun createFineTune(request: CreateFineTuneRequest): CreateFineTuneResponse {
        return httpClient.post(request, FINE_TUNES_ENDPOINT)
    }

    override suspend fun cancelFineTune(request: CancelFineTuneRequest): CancelFineTuneResponse {
        return httpClient.post(null, "$FINE_TUNES_ENDPOINT/${request.fineTuneId}/cancel")
    }

    override suspend fun listFineTunes(): ListFineTunesResponse {
        return httpClient.get(FINE_TUNES_ENDPOINT)
    }

    override suspend fun deleteFineTuneModel(request: DeleteFineTuneRequest): DeleteFineTuneResponse {
        return httpClient.delete("$FINE_TUNES_MODELS_ENDPOINT/${request.modelId}/delete")
    }

    companion object {
        private const val FINE_TUNES_ENDPOINT = "v1/fine-tunes"
        private const val FINE_TUNES_MODELS_ENDPOINT = "v1/models"
    }
}
