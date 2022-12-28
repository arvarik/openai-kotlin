package org.arvarik.openai.core.api.images

import kotlinx.serialization.Serializable
import org.arvarik.openai.core.api.OpenAIResponse

/**
 * Response object for the /images/generations endpoint
 *
 * Creates an image given a prompt
 *
 * @see [Create Image API](https://beta.openai.com/docs/api-reference/images/create)
 */
@Serializable
data class CreateImageResponse(
    /**
     * The creation time in epoch seconds
     */
    val created: Long,

    /**
     * The list of generated images
     */
    val data: List<Image>
) : OpenAIResponse

/**
 * Object to represent an OpenAI generated Image
 */
@Serializable
data class Image(
    /**
     * The url location of the image
     */
    val url: String
)
