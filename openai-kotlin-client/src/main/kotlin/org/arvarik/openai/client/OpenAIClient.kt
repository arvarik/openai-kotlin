package org.arvarik.openai.client

import org.arvarik.openai.client.http.OpenAIHTTPClient
import org.arvarik.openai.client.internal.impl.OpenAIClientImpl
import org.arvarik.openai.core.api.completions.CreateCompletionRequest
import org.arvarik.openai.core.api.completions.CreateCompletionResponse
import org.arvarik.openai.core.api.edits.CreateEditRequest
import org.arvarik.openai.core.api.edits.CreateEditResponse

interface OpenAIClient : Completions, Edits // , Models, Images... TODO: Rest of clients


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

interface Embeddings

interface Files

interface FineTunes

interface Moderations

fun OpenAIClient(config: OpenAIClientConfig): OpenAIClient {
    return OpenAIClientImpl(OpenAIHTTPClient(config))
}
