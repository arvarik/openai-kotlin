package org.arvarik.openai.core.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Usage(
    /**
     * The number of prompt tokens used
     */
    @SerialName("prompt_tokens")
    val promptTokens: Long? = null,
    /**
     * The number of tokens in the completion
     */
    @SerialName("completion_tokens")
    val completionTokens: Long? = null,
    /**
     * The number of total tokens used
     */
    @SerialName("total_tokens")
    val totalTokens: Long? = null,
)
