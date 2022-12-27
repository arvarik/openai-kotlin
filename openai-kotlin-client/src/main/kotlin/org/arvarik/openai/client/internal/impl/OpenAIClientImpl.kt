package org.arvarik.openai.client.internal.impl

import org.arvarik.openai.client.Completions
import org.arvarik.openai.client.Edits
import org.arvarik.openai.client.Embeddings
import org.arvarik.openai.client.Moderations
import org.arvarik.openai.client.OpenAIClient
import org.arvarik.openai.client.http.OpenAIHTTPClient

internal class OpenAIClientImpl(
    private val httpClient: OpenAIHTTPClient
) : OpenAIClient,
        Completions by CompletionsImpl(httpClient),
        Edits by EditsImpl(httpClient),
        Embeddings by EmbeddingsImpl(httpClient),
        Moderations by ModerationsImpl(httpClient)
