package org.arvarik.openai.core.api.edits

import kotlinx.serialization.Serializable
import org.arvarik.openai.core.api.OpenAIResponse
import org.arvarik.openai.core.api.Usage

/**
 * Response object for the /edits endpoint
 *
 * Given a prompt and an instruction, the model will return an edited version of the prompt
 *
 * @see [Create Edit API](https://beta.openai.com/docs/api-reference/edits/create)
 */
@Serializable
data class CreateEditResponse(
    /**
     * The object type of the response
     */
    val `object`: String,

    /**
     * The creation time in epoch seconds
     */
    val created: Long,

    /**
     * The list of generated completions with associated logprobs if applicable
     */
    val choices: List<Edit>,

    /**
     * The API resources used by the associated request
     */
    val usage: Usage
) : OpenAIResponse

/**
 * A singular GPT-3 model edit
 */
@Serializable
data class Edit(
    /**
     * The generated response text
     */
    val text: String,

    /**
     * The index of the completion choice in the returned list
     */
    val index: Int
)
