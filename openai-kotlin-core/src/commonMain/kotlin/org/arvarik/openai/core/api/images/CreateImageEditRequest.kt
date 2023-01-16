package org.arvarik.openai.core.api.images

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.arvarik.openai.core.api.OpenAIRequest

/**
 * Request object for the /images/edits endpoint
 *
 * Creates an edited or extended image given an original image and a prompt
 *
 * @see [Create Image Edit API](https://beta.openai.com/docs/api-reference/images/create-edit)
 */
@Serializable
data class CreateImageEditRequest(
    /**
     * The image to edit. Must be a valid PNG file, less than 4MB, and square. If mask is not provided, image must have
     * transparency, which will be used as the mask
     */
    val image: String,

    /**
     * An additional image whose fully transparent areas (e.g. where alpha is zero) indicate where image should be
     * edited. Must be a valid PNG file, less than 4MB, and have the same dimensions as image
     */
    val mask: String? = null,

    /**
     * ext description of the desired image(s). The maximum length is 1000 characters
     */
    val prompt: String,

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
    val user: String? = null
) : OpenAIRequest
