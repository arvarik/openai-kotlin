package org.arvarik.openai.client.internal.impl

import org.arvarik.openai.client.Moderations
import org.arvarik.openai.client.internal.http.OpenAIHTTPClient
import org.arvarik.openai.core.api.moderations.CreateModerationRequest
import org.arvarik.openai.core.api.moderations.CreateModerationResponse

internal class ModerationsImpl(private val httpClient: OpenAIHTTPClient) : Moderations {
    override suspend fun createModeration(request: CreateModerationRequest): CreateModerationResponse {
        return httpClient.post(request, MODERATIONS_ENDPOINT)
    }

    companion object {
        private const val MODERATIONS_ENDPOINT = "v1/moderations"
    }
}
