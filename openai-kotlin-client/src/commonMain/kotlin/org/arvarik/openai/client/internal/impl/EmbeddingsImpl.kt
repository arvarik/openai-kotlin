package org.arvarik.openai.client.internal.impl

import org.arvarik.openai.client.Embeddings
import org.arvarik.openai.client.internal.http.OpenAIHTTPClient
import org.arvarik.openai.core.api.embeddings.CreateEmbeddingsRequest
import org.arvarik.openai.core.api.embeddings.CreateEmbeddingsResponse

internal class EmbeddingsImpl(private val httpClient: OpenAIHTTPClient) : Embeddings {

    override suspend fun createEmbeddings(request: CreateEmbeddingsRequest): CreateEmbeddingsResponse {
        return httpClient.post(request, EmbeddingsEndpoint)
    }

    companion object {
        private const val EmbeddingsEndpoint = "v1/embeddings"
    }
}
