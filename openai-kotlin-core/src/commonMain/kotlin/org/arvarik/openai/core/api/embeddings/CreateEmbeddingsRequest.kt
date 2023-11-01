package org.arvarik.openai.core.api.embeddings

import kotlinx.serialization.Serializable
import org.arvarik.openai.core.api.OpenAIRequest

/**
 * Request object for the /embeddings endpoint
 *
 * Given a model and input text, creates an embedding vector representing the input text
 *
 * @see [Create Embeddings API](https://beta.openai.com/docs/api-reference/embeddings/create)
 */
@Serializable
data class CreateEmbeddingsRequest(
    /**
     * ID of the model to use
     */
    val model: String,
    /**
     * Input text to get embeddings for, encoded as a string or array of tokens. To get embeddings
     * for multiple inputs in a single request, pass an array of strings or array of token arrays.
     * Each input must not exceed 8192 tokens in length.
     */
    val input: List<String>,
    /**
     * A unique identifier representing your end-user, which can help OpenAI to monitor and
     * detect abuse
     */
    val user: String? = null,
) : OpenAIRequest
