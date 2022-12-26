package org.arvarik.openai.core.api.embeddings

import kotlinx.serialization.Serializable
import org.arvarik.openai.core.api.OpenAIResponse
import org.arvarik.openai.core.api.Usage

@Serializable
data class CreateEmbeddingsResponse(
    /**
     * The object type of the response
     */
    val `object`: String,

    /**
     * The embedding vectors associated with the input texts from the request
     */
    val data: List<Embedding>,

    /**
     * The GPT-3 model used
     */
    val model: String,

    /**
     * The API resources used by the associated request
     */
    val usage: Usage
) : OpenAIResponse


@Serializable
data class Embedding(
    /**
     * The object type of the response
     */
    val `object`: String,

    /**
     * The embedding vector
     */
    val embedding: List<Double>,

    /**
     * The index of the embedding with respect to the input text
     */
    val index: Int
)