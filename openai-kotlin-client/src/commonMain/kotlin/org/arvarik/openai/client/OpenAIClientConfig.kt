package org.arvarik.openai.client

import kotlin.time.Duration

/**
 * The OpenAI client configuration needed to instantiate the client
 *
 * @param token The API token to use for the client
 * @param timeout The request timeout, default is 30 seconds
 * @param imagesFilepath The filepath of the directory containing images to be used for the Images APIs
 */
data class OpenAIClientConfig(
    val token: String,
    val timeout: HttpTimeout? = null,
    val imagesFilepath: String? = null,
)

/**
 * Data class to hold http timeout configurations
 *
 * @param connect The max time in which a client should establish a connection with server
 * @param request The max time to process the entire HTTP call
 * @param socket The max time between reception of any two data packets
 */
data class HttpTimeout(
    val connect: Duration? = null,
    val request: Duration? = null,
    val socket: Duration? = null,
)
