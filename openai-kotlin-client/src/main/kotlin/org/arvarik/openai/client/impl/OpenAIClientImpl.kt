package org.arvarik.openai.client.impl

import org.arvarik.openai.client.Completions
import org.arvarik.openai.client.OpenAIClient
import org.arvarik.openai.client.http.OpenAIHTTPClient

internal class OpenAIClientImpl(
    private val httpClient: OpenAIHTTPClient
) : OpenAIClient,
        Completions by CompletionsImpl(httpClient)
