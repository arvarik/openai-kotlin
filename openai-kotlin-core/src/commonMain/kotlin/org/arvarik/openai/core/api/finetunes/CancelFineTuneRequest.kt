package org.arvarik.openai.core.api.finetunes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.arvarik.openai.core.api.OpenAIRequest

/**
 * Request object for the cancel fine-tune <a href="https://beta.openai.com/docs/api-reference/fine-tunes/cancel"> API </a>.
 */
@Serializable
data class CancelFineTuneRequest(
    /**
     * The ID of the fine-tune request that will be cancelled.
     */
    @SerialName("fine_tune_id")
    val fineTuneId: String

) : OpenAIRequest
