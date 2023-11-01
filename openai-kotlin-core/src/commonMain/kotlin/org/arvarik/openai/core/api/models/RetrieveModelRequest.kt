package org.arvarik.openai.core.api.models

import kotlinx.serialization.Serializable
import org.arvarik.openai.core.api.OpenAIRequest

/**
 * Request object for the /models endpoint
 *
 * Retrieves model metadata
 *
 * @see [Retrieve Model API](]https://beta.openai.com/docs/api-reference/models/retrieve)
 */

@Serializable
data class RetrieveModelRequest(
    /**
     * ID of the model to use for request
     */
    val model: String,
) : OpenAIRequest
