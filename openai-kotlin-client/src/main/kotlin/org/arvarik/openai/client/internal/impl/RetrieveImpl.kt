package org.arvarik.openai.client.internal.impl

import org.arvarik.openai.client.Models
import org.arvarik.openai.client.internal.http.OpenAIHTTPClient
import org.arvarik.openai.core.api.moderations.CreateRetrieveRequest
import org.arvarik.openai.core.api.moderations.CreateRetrieveResponse

internal class RetrieveModelsImpl(private val httpClient: OpenAIHTTPClient) : Models {

    override suspend fun retrieveModel(request: CreateRetrieveRequest): CreateRetrieveResponse {
        return httpClient.get("RetrieveModelsEndpoint${request.model}")
    }

    companion object {
        private const val RetrieveModelsEndpoint = "v1/models/"
    }
}