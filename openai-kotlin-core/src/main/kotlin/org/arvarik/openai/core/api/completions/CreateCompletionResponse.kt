package org.arvarik.openai.core.api.completions

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.arvarik.openai.core.api.Usage


/**
 * Response object for the /completions endpoint
 *
 * Creates a completion for the provided prompt and parameters
 *
 * Documentation: https://beta.openai.com/docs/api-reference/completions
 */
@Serializable
data class CreateCompletionResponse(
    /**
     * The id associated with the completion
     */
    val id: String,

    /**
     * The object type of the response
     */
    val `object`: String,

    /**
     * The creation time in epoch seconds
     */
    val created: Long,

    /**
     * The GPT-3 model used
     */
    val model: String,

    /**
     * The list of generated completions with associated logprobs if applicable
     */
    val choices: List<Completion>,

    /**
     * The API resources used by the associated request
     */
    val usage: Usage
)

/**
 * A singular GPT-3 model completion
 */
@Serializable
data class Completion(
    /**
     * The generated response text
     */
    val text: String,

    /**
     * The index of the completion choice in the returned list
     */
    val index: Int,

    /**
     * The log probabilities of the given prompt tokens
     */
    val logprobs: Logprobs?,

    /**
     * The reason why the GPT-3 model finished generating
     */
    @SerialName("finish_reason")
    val finishReason: String
)

/**
 * Log probabilities of the given prompt tokens. Will populate if logprobs under
 * {@link CompletionRequest} is greater than zero
 */
@Serializable
data class Logprobs(
    /**
     * The list of tokens from the prompt
     */
    val tokens: List<String>,

    /**
     * The log probability of each of the tokens
     */
    @SerialName("token_logprobs")
    val tokenLogprobs: List<Double>,

    /**
     * The top tokens in logprobs and their associated probabilities
     */
    @SerialName("top_logprobs")
    val topLogprobs: List<Map<String, Double>>,

    /**
     * The character offset from the start of the returned text for each of the tokens
     */
    @SerialName("text_offset")
    val textOffset: List<Int>
)
