package org.arvarik.openai.core.api.moderations

import kotlinx.serialization.Serializable
import org.arvarik.openai.core.api.OpenAIRequest

/**
 * Request object for the /moderations endpoint
 *
 * Given an input text, outputs if the model classifies it as violating OpenAI's content policy
 *
 * @see [Create Moderation API](https://beta.openai.com/docs/api-reference/moderations/create)
 */
@Serializable
data class CreateModerationRequest(
    /**
     * A list of input text to classify
     */
    val input: List<String>,

    /**
     * Two content moderations models are available: text-moderation-stable and text-moderation-latest
     *
     * The default is text-moderation-latest which will be automatically upgraded over time. This ensures you are
     * always using our most accurate model. If you use text-moderation-stable, we will provide advanced notice before
     * updating the model. Accuracy of text-moderation-stable may be slightly lower than for text-moderation-latest
     */
    val model: String? = null
) : OpenAIRequest
