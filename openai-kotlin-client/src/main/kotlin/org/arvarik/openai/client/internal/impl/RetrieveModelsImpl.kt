package org.arvarik.openai.client.internal.impl

import org.arvarik.openai.client.Models
import org.arvarik.openai.client.http.OpenAIHTTPClient
import org.arvarik.openai.core.api.retrieve.CreateRetrieveRequest
import org.arvarik.openai.core.api.retrieve.CreateRetrieveResponse

internal class RetrieveModelsImpl(private val httpClient: OpenAIHTTPClient) : Models {

    override suspend fun retrieveModels(request: CreateRetrieveRequest): CreateRetrieveResponse {
        return httpClient.get(RetrieveModelsEndpoint)
//        ${request.model}
    }

    companion object {
        private const val RetrieveModelsEndpoint = "v1/models/text-davinci-edit-001"
    }
}
