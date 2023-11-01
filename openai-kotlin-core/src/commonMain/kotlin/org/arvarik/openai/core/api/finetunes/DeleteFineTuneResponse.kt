package org.arvarik.openai.core.api.finetunes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.arvarik.openai.core.api.OpenAIResponse

/**
 * Response object for the delete fine-tune model @see [API](https://platform.openai.com/docs/api-reference/fine-tunes/delete-model)
 */
@Serializable
data class DeleteFineTuneResponse(
    /**
     * The ID of the fine-tune model deleted.
     */
    @SerialName("id")
    val modelId: String,
    /**
     * The type of this object.
     */
    @SerialName("object")
    val objectType: String,
    /**
     * Boolean that indicate if the deletion succeeded or not.
     */
    val deleted: Boolean,
) : OpenAIResponse
