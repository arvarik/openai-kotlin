package org.arvarik.openai.core.api.images

import kotlinx.serialization.Serializable
import org.arvarik.openai.core.api.OpenAIResponse

/**
 * Response object for the /images/variations endpoint
 *
 * Creates a variation of a given image
 *
 * @see [Create Image Variation API](https://beta.openai.com/docs/api-reference/images/create-variation)
 */
@Serializable
data class CreateImageVariationResponse(
    /**
     * The creation time in epoch seconds
     */
    val created: Long,

    /**
     * The list of generated images
     */
    val data: List<Image>
) : OpenAIResponse
