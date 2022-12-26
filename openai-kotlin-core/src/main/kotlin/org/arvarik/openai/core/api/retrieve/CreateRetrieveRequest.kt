package org.arvarik.openai.core.api.retrieve

import kotlinx.serialization.SerialName
//when to use serialname bc I did not use for this class but CreateCompletionsRequest did
import kotlinx.serialization.Serializable
import org.arvarik.openai.core.api.OpenAIRequest


/**
 * Documentation: https://beta.openai.com/docs/api-reference/completions/models/retrieve
 */

//@Serializable
//Still need to understand usecase of keyword
data class CreateRetrieveRequest(
    /**
     * ID of the model to use for request
     */
    val model: String,
) : OpenAIRequest