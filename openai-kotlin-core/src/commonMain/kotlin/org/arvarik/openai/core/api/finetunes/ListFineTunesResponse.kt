package org.arvarik.openai.core.api.finetunes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.arvarik.openai.core.api.OpenAIResponse

/**
 * Response object of the list Fine-tunes API.
 *
 * @see [List Fine-Tune API](https://beta.openai.com/docs/api-reference/fine-tunes/list).
 */
@Serializable
data class ListFineTunesResponse(
    /**
     * The response object type. Example, fine-tune
     */
    @SerialName("object")
    val objectType: String,

    /**
     * List of fine-tunes.
     */
    val data: List<FineTune> = listOf(),
) : OpenAIResponse
