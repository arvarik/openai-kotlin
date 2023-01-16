package org.arvarik.openai.client.internal.impl

import org.arvarik.openai.client.Completions
import org.arvarik.openai.client.Edits
import org.arvarik.openai.client.Embeddings
import org.arvarik.openai.client.FineTunes
import org.arvarik.openai.client.Images
import org.arvarik.openai.client.Models
import org.arvarik.openai.client.Moderations
import org.arvarik.openai.client.OpenAIClient
import org.arvarik.openai.client.OpenAIClientConfig
import org.arvarik.openai.client.internal.http.OpenAIHTTPClient
import org.arvarik.openai.client.internal.io.LocalFileReader
import org.arvarik.openai.client.internal.io.LocalFileSystem

internal class OpenAIClientImpl(
    private val config: OpenAIClientConfig,
    private val httpClient: OpenAIHTTPClient = OpenAIHTTPClient(config),
    private val localFileReader: LocalFileReader = LocalFileReader(config, LocalFileSystem)
) : OpenAIClient,
    Completions by CompletionsImpl(httpClient),
    Edits by EditsImpl(httpClient),
    Images by ImagesImpl(httpClient, localFileReader),
    Embeddings by EmbeddingsImpl(httpClient),
    Models by ModelsImpl(httpClient),
    Moderations by ModerationsImpl(httpClient),
    FineTunes by FineTunesImpl(httpClient)
