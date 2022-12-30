package org.arvarik.openai.client.internal.impl

import okio.FileSystem
import org.arvarik.openai.client.Completions
import org.arvarik.openai.client.Models
import org.arvarik.openai.client.Edits
import org.arvarik.openai.client.Embeddings
import org.arvarik.openai.client.Images
import org.arvarik.openai.client.Moderations
import org.arvarik.openai.client.OpenAIClient
import org.arvarik.openai.client.OpenAIClientConfig
import org.arvarik.openai.client.internal.http.OpenAIHTTPClient
import org.arvarik.openai.client.internal.io.LocalFileSystem

private val fileSystem = FileSystem.SYSTEM

internal class OpenAIClientImpl(
    private val config: OpenAIClientConfig,
    private val httpClient: OpenAIHTTPClient = OpenAIHTTPClient(config),
    private val localFileSystem: LocalFileSystem = LocalFileSystem(config, fileSystem)
) : OpenAIClient,
    Completions by CompletionsImpl(httpClient),
    Models by ModelsImpl(httpClient),
    Edits by EditsImpl(httpClient),
    Images by ImagesImpl(httpClient, localFileSystem),
    Embeddings by EmbeddingsImpl(httpClient),
    Moderations by ModerationsImpl(httpClient)
