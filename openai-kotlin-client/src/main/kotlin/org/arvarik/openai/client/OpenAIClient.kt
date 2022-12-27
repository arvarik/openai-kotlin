package org.arvarik.openai.client

import org.arvarik.openai.client.http.OpenAIHTTPClient
import org.arvarik.openai.client.internal.impl.OpenAIClientImpl
import org.arvarik.openai.core.api.completions.CreateCompletionRequest
import org.arvarik.openai.core.api.completions.CreateCompletionResponse
import org.arvarik.openai.core.api.edits.CreateEditRequest
import org.arvarik.openai.core.api.edits.CreateEditResponse
import org.arvarik.openai.core.api.embeddings.CreateEmbeddingsRequest
import org.arvarik.openai.core.api.embeddings.CreateEmbeddingsResponse
import org.arvarik.openai.core.api.moderations.CreateModerationRequest
import org.arvarik.openai.core.api.moderations.CreateModerationResponse

interface OpenAIClient : Completions, Edits, Embeddings, Moderations // , Models, Images... TODO: Rest of clients


interface Completions {

    /**
     * Creates a completion request to the GPT-3 /completions endpoint
     *
     * @param request The given completion request object
     * @return The generated completion response
     */
    suspend fun createCompletion(request: CreateCompletionRequest): CreateCompletionResponse
}

interface Models

interface Edits {

    /**
     * Creates an edit request to the GPT-3 /edits endpoint
     *
     * @param request The given edit request object
     * @return The generated edit response
     */
    suspend fun createEdit(request: CreateEditRequest): CreateEditResponse
}

interface Images

interface Embeddings {

    /**
     * Creates an embedding vector representing the input text
     *
     * @param request The given create embeddings request
     * @return The embedding vector that represents the input text
     */
    suspend fun createEmbeddings(request: CreateEmbeddingsRequest): CreateEmbeddingsResponse
}

interface Files

interface FineTunes

interface Moderations {

    /**
     * Classifies if text violates OpenAI's Content Policy
     *
     * @param request The given create moderation request
     * @return The moderation results indicating if the given input text violates OpenAI's Content Policy
     */
    suspend fun createModeration(request: CreateModerationRequest): CreateModerationResponse
}

fun OpenAIClient(config: OpenAIClientConfig): OpenAIClient {
    return OpenAIClientImpl(OpenAIHTTPClient(config))
}
