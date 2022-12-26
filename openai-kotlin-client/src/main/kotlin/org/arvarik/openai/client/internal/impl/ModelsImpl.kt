package org.arvarik.openai.client.internal.impl

import org.arvarik.openai.client.Models
import org.arvarik.openai.client.http.OpenAIHTTPClient
import org.arvarik.openai.core.api.models.ListModels.ListModelsResponse

internal class ModelsImpl(private val httpClient: OpenAIHTTPClient) : Models {

    override suspend fun listModels(): ListModelsResponse {
        return httpClient.get(ListModelsEndpoint)
    }

    companion object {
        private const val ListModelsEndpoint = "v1/models"
    }
}