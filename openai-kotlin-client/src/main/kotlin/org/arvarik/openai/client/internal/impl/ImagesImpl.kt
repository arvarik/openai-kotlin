package org.arvarik.openai.client.internal.impl

import org.arvarik.openai.client.Images
import org.arvarik.openai.client.http.OpenAIHTTPClient
import org.arvarik.openai.core.api.images.CreateImageRequest
import org.arvarik.openai.core.api.images.CreateImageResponse

internal class ImagesImpl(private val httpClient: OpenAIHTTPClient) : Images {

    override suspend fun createImage(request: CreateImageRequest): CreateImageResponse {
        return httpClient.post(request, ImagesGenerationsEndpoint)
    }

    companion object {
        private const val ImagesGenerationsEndpoint = "/v1/images/generations"
    }
}
