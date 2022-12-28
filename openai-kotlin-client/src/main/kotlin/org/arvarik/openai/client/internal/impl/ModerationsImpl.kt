package org.arvarik.openai.client.internal.impl

import org.arvarik.openai.client.Moderations
import org.arvarik.openai.client.http.OpenAIHTTPClient
import org.arvarik.openai.core.api.moderations.CreateModerationRequest
import org.arvarik.openai.core.api.moderations.CreateModerationResponse

internal class ModerationsImpl(private val httpClient: OpenAIHTTPClient) : Moderations {

    override suspend fun createModeration(request: CreateModerationRequest): CreateModerationResponse {
        return httpClient.post(request, ModerationsEndpoint)
    }

    companion object {
        private const val ModerationsEndpoint = "v1/moderations"
    }
}
