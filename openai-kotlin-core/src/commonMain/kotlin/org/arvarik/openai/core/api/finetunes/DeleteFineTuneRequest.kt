package org.arvarik.openai.core.api.finetunes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.arvarik.openai.core.api.OpenAIRequest

/**
 * Request object for the cancel fine-tune @see [API](https://beta.openai.com/docs/api-reference/fine-tunes/cancel)
 */
@Serializable
data class DeleteFineTuneRequest(
    /**
     * The ID of the fine-tune model to be deleted
     */
    @SerialName("model")
    val modelId: String

) : OpenAIRequest
