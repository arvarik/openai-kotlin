package org.arvarik.openai.core.api.images

import kotlinx.serialization.Serializable

/**
 * Object to represent an OpenAI generated Image
 */
@Serializable
data class Image(
    /**
     * The url location of the image
     */
    val url: String,
)
