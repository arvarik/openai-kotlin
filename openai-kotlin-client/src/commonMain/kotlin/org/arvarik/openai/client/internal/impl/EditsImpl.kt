package org.arvarik.openai.client.internal.impl

import org.arvarik.openai.client.Edits
import org.arvarik.openai.client.internal.http.OpenAIHTTPClient
import org.arvarik.openai.core.api.edits.CreateEditRequest
import org.arvarik.openai.core.api.edits.CreateEditResponse

internal class EditsImpl(private val httpClient: OpenAIHTTPClient) : Edits {
    override suspend fun createEdit(request: CreateEditRequest): CreateEditResponse {
        return httpClient.post(request, EDITS_ENDPOINT)
    }

    companion object {
        private const val EDITS_ENDPOINT = "v1/edits"
    }
}
