package org.arvarik.openai.core.api.images

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.arvarik.openai.core.api.OpenAIRequest

/**
 * Request object for the /images/variations endpoint
 *
 * Creates a variation of a given image
 *
 * @see [Create Image Variation API](https://beta.openai.com/docs/api-reference/images/create-variation)
 */
@Serializable
data class CreateImageVariationRequest(
    /**
     * The image to use as the basis for the variation(s). Must be a valid PNG file, less than 4MB, and square
     */
    val image: String,
    /**
     * The number of images to generate. Must be between 1 and 10
     */
    val n: Int? = null,
    /**
     * The size of the generated images. Must be one of 256x256, 512x512, or 1024x1024
     */
    val size: String? = null,
    /**
     * The format in which the generated images are returned. Must be one of url or b64_json
     */
    @SerialName("response_format")
    val responseFormat: String? = null,
    /**
     * A unique identifier representing your end-user, which can help OpenAI to monitor and detect abuse
     */
    val user: String? = null,
) : OpenAIRequest
