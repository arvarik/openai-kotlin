package org.arvarik.openai.core.api.retrieve

import kotlinx.serialization.Serializable
import org.arvarik.openai.core.api.OpenAIRequest

/**
 * Documentation: https://beta.openai.com/docs/api-reference/models/retrieve
 */

@Serializable
data class CreateRetrieveModelRequest(
    /**
     * ID of the model to use for request
     */
    val model: String

) : OpenAIRequest
