package org.arvarik.openai.client.internal.impl

import org.arvarik.openai.client.Models
import org.arvarik.openai.client.internal.http.OpenAIHTTPClient
import org.arvarik.openai.core.api.models.RetrieveModelRequest
import org.arvarik.openai.core.api.models.RetrieveModelResponse

internal class ModelsImpl(private val httpClient: OpenAIHTTPClient) : Models {
    override suspend fun retrieveModel(request: RetrieveModelRequest): RetrieveModelResponse {
        return httpClient.get("${RetrieveModelsEndpoint}${request.model}")
    }

    companion object {
        private const val RetrieveModelsEndpoint = "v1/models/"
    }
}
