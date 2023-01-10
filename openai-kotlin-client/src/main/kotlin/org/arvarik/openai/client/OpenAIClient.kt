package org.arvarik.openai.client

import org.arvarik.openai.client.internal.impl.OpenAIClientImpl
import org.arvarik.openai.core.api.completions.CreateCompletionRequest
import org.arvarik.openai.core.api.completions.CreateCompletionResponse
import org.arvarik.openai.core.api.edits.CreateEditRequest
import org.arvarik.openai.core.api.edits.CreateEditResponse
import org.arvarik.openai.core.api.embeddings.CreateEmbeddingsRequest
import org.arvarik.openai.core.api.embeddings.CreateEmbeddingsResponse
import org.arvarik.openai.core.api.images.CreateImageEditRequest
import org.arvarik.openai.core.api.images.CreateImageEditResponse
import org.arvarik.openai.core.api.images.CreateImageRequest
import org.arvarik.openai.core.api.images.CreateImageResponse
import org.arvarik.openai.core.api.images.CreateImageVariationRequest
import org.arvarik.openai.core.api.images.CreateImageVariationResponse
import org.arvarik.openai.core.api.models.ListModels.ListModelsResponse
import org.arvarik.openai.core.api.moderations.CreateModerationRequest
import org.arvarik.openai.core.api.moderations.CreateModerationResponse

interface OpenAIClient : Completions, Models, Edits, Images, Embeddings, Moderations // TODO: Rest of clients

interface Completions {

    /**
     * Creates a completion request to the GPT-3 /completions endpoint
     *
     * @param request The given completion request object
     * @return The generated completion response
     */
    suspend fun createCompletion(request: CreateCompletionRequest): CreateCompletionResponse
}

interface Models {

    /**
     * Gets the currently available models, along with basic information about each such as the owner and availability, from the /models endpoint
     *
     * @return The generated list models response
     */
    suspend fun listModels(): ListModelsResponse
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

interface Images {

    /**
     * Creates an image given a prompt
     *
     * @param request The given create image request
     * @return The generated image
     */
    suspend fun createImage(request: CreateImageRequest): CreateImageResponse

    /**
     * Creates an edited or extended image given an original image and a prompt
     *
     * @param request The given create image edit request
     * @return The generated edited image
     */
    suspend fun createImageEdit(request: CreateImageEditRequest): CreateImageEditResponse

    /**
     * Creates a variation of a given image
     *
     * @param request The given create image variation request
     * @return The generated image variation
     */
    suspend fun createImageVariation(request: CreateImageVariationRequest): CreateImageVariationResponse
}

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
    return OpenAIClientImpl(config)
}
