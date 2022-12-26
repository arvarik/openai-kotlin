package org.arvarik.openai.client

import org.arvarik.openai.client.http.OpenAIHTTPClient
import org.arvarik.openai.client.internal.impl.OpenAIClientImpl
import org.arvarik.openai.core.api.models.ListModels.ListModelsResponse
import org.arvarik.openai.core.api.completions.CreateCompletionRequest
import org.arvarik.openai.core.api.completions.CreateCompletionResponse
import org.arvarik.openai.core.api.edits.CreateEditRequest
import org.arvarik.openai.core.api.edits.CreateEditResponse
import org.arvarik.openai.core.api.embeddings.CreateEmbeddingsRequest
import org.arvarik.openai.core.api.embeddings.CreateEmbeddingsResponse

interface OpenAIClient : Completions, Edits, Models, Embeddings // Images... TODO: Rest of clients


interface Models {

    /**
     * Gets the currently available models, along with basic information about each such as the owner and availability, from the /models endpoint
     *
     * @return The generated list models response
     */
    suspend fun listModels(): ListModelsResponse

}

interface Completions {

    /**
     * Creates a completion request to the GPT-3 /completions endpoint
     *
     * @param request The given completion request object
     * @return The generated completion response
     */
    suspend fun createCompletion(request: CreateCompletionRequest): CreateCompletionResponse
}

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

interface Moderations

fun OpenAIClient(config: OpenAIClientConfig): OpenAIClient {
    return OpenAIClientImpl(OpenAIHTTPClient(config))
}
