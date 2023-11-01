package org.arvarik.openai.client.exception

/**
 * Exception to throw when there is a runtime client exception
 */
class OpenAIClientException(
    message: String? = null,
    throwable: Throwable? = null,
) : RuntimeException(message, throwable)

/**
 * Exception to throw when there is server exception
 */
class OpenAIServerException(
    status: Int,
    body: String,
) : RuntimeException("$status: $body")
