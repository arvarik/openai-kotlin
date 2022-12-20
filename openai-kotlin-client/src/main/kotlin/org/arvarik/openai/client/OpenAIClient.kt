package org.arvarik.openai.client

import org.arvarik.openai.client.http.OpenAIHTTPClient
import org.arvarik.openai.client.impl.OpenAIClientImpl
import org.arvarik.openai.core.api.completions.CreateCompletionRequest
import org.arvarik.openai.core.api.completions.CreateCompletionResponse

interface OpenAIClient : Completions // , Models, Edits, Images... TODO: Rest of clients


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

interface Edits

interface Images

interface Embeddings

interface Files

interface FineTunes

interface Moderations

fun OpenAIClient(config: OpenAIClientConfig): OpenAIClient {
    return OpenAIClientImpl(OpenAIHTTPClient(config))
}
