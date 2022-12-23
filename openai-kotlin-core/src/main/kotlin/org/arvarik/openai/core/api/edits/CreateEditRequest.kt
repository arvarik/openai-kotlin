package org.arvarik.openai.core.api.edits

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.arvarik.openai.core.api.OpenAIRequest


/**
 * Request object for the /edits endpoint
 *
 * Given a prompt and an instruction, the model will return an edited version of the prompt
 *
 * https://beta.openai.com/docs/api-reference/edits/create
 */
@Serializable
data class CreateEditRequest(
    /**
     * ID of the model to use
     */
    val model: String,

    /**
     * The input text to use as a starting point for the edit
     */
    val input: String? = null,

    /**
     * The instruction that tells the model how to edit the prompt
     */
    val instruction: String,

    /**
     * How many edits to generate for the input and instruction
     *
     * Note: Because this parameter generates many completions, it can quickly consume your token
     * quota. Use carefully and ensure that you have reasonable settings for max_tokens and stop
     */
    val n: Int? = null,

    /**
     * What sampling temperature to use. Higher values means the model will take more risks. Try
     * 0.9 for more creative applications, and 0 (argmax sampling) for ones with a well-defined
     * answer
     *
     * We generally recommend altering this or top_p but not both
     */
    val temperature: Double? = null,

    /**
     * An alternative to sampling with temperature, called nucleus sampling, where the model
     * considers the results of the tokens with top_p probability mass. So 0.1 means only the tokens
     * comprising the top 10% probability mass are considered
     *
     * We generally recommend altering this or temperature but not both
     */
    @SerialName("top_p")
    val topP: Double? = null
) : OpenAIRequest
