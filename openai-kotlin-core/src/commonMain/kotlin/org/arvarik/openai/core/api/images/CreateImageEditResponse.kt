package org.arvarik.openai.core.api.images

import kotlinx.serialization.Serializable
import org.arvarik.openai.core.api.OpenAIResponse

/**
 * Response object for the /images/edits endpoint
 *
 * Creates an edited or extended image given an original image and a prompt
 *
 * @see [Create Image Edit API](https://beta.openai.com/docs/api-reference/images/create-edit)
 */
@Serializable
data class CreateImageEditResponse(
    /**
     * The creation time in epoch seconds
     */
    val created: Long,
    /**
     * The list of generated images
     */
    val data: List<Image>,
) : OpenAIResponse
