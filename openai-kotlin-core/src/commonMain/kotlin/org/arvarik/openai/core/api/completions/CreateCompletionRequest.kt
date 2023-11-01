package org.arvarik.openai.core.api.completions

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.arvarik.openai.core.api.OpenAIRequest

/**
 * Request object for the /completions endpoint
 *
 * Creates a completion for the provided prompt and parameters
 *
 * @see [Create Completion API](https://beta.openai.com/docs/api-reference/completions/create)
 */
@Serializable
data class CreateCompletionRequest(
    /**
     * ID of the model to use
     */
    val model: String,
    /**
     * The prompt to generate completions for
     */
    val prompt: String? = null,
    /**
     * The suffix that comes after a completion of inserted text
     */
    val suffix: String? = null,
    /**
     * The maximum number of tokens to generate in the completion
     */
    @SerialName("max_tokens")
    val maxTokens: Int? = null,
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
    val topP: Double? = null,
    /**
     * How many completions to generate for each prompt
     *
     * Note: Because this parameter generates many completions, it can quickly consume your token
     * quota. Use carefully and ensure that you have reasonable settings for max_tokens and stop
     */
    val n: Int? = null,
    /**
     * Whether to stream back partial progress. If set, tokens will be sent as data-only server-sent
     * events as they become available, with the stream terminated by a data: DONE message
     */
    val stream: Boolean? = null,
    /**
     * Include the log probabilities on the logprobs most likely tokens, as well the chosen tokens.
     * For example, if logprobs is 5, the API will return a list of the 5 most likely tokens. The
     * API will always return the logprob of the sampled token, so there may be up to logprobs+1
     * elements in the response
     */
    val logprobs: Int? = null,
    /**
     * Echo back the prompt in addition to the completion
     */
    val echo: Boolean? = null,
    /**
     * Up to 4 sequences where the API will stop generating further tokens. The returned text will
     * not contain the stop sequence
     */
    val stop: String? = null,
    /**
     * Number between -2.0 and 2.0. Positive values penalize new tokens based on whether they appear
     * in the text so far, increasing the model's likelihood to talk about new topics
     */
    @SerialName("presence_penalty")
    val presencePenalty: Double? = null,
    /**
     * Number between -2.0 and 2.0. Positive values penalize new tokens based on their existing
     * frequency in the text so far, decreasing the model's likelihood to repeat the same line
     * verbatim
     */
    @SerialName("frequency_penalty")
    val frequencyPenalty: Double? = null,
    /**
     * Generates best_of completions server-side and returns the "best" (the one with the highest
     * log probability per token). Results cannot be streamed
     *
     * When used with n, best_of controls the number of candidate completions and n specifies how
     * many to return – best_of must be greater than n
     *
     * Note: Because this parameter generates many completions, it can quickly consume your token
     * quota. Use carefully and ensure that you have reasonable settings for max_tokens and stop
     */
    @SerialName("best_of")
    val bestOf: Int? = null,
    /**
     * Modify the likelihood of specified tokens appearing in the completion
     *
     * Accepts a json object that maps tokens (specified by their token ID in the GPT tokenizer) to
     * an associated bias value from -100 to 100
     *
     * Tokenizer Tool: https://beta.openai.com/tokenizer?view=bpe
     */
    @SerialName("logit_bias")
    val logitBias: Map<String, Int>? = null,
    /**
     * A unique identifier representing your end-user, which can help OpenAI to monitor and detect
     * abuse
     */
    val user: String? = null,
) : OpenAIRequest
