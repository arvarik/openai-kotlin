package com.arvarik.openai.example.app

import kotlinx.coroutines.runBlocking
import org.arvarik.openai.client.OpenAIClient
import org.arvarik.openai.client.OpenAIClientConfig
import org.arvarik.openai.core.api.GPT3Model
import org.arvarik.openai.core.api.completions.CreateCompletionRequest
import org.arvarik.openai.core.api.edits.CreateEditRequest
import org.arvarik.openai.core.api.embeddings.CreateEmbeddingsRequest

fun main() = runBlocking {
    val openaiApiKey = System.getenv("OPENAI_API_KEY")
    val token = requireNotNull(openaiApiKey) { "ERROR: OPENAI_API_KEY env variable not set" }
    val openAI = OpenAIClient(OpenAIClientConfig(token))
    val model = GPT3Model.DAVINCI.modelName

    // Completions API
    val createCompletionRequest = CreateCompletionRequest(
        model = model,
        prompt = "Give me 3 names for my pet squirrel",
        maxTokens = 20,
        temperature = 0.7
    )

    println("Calling /completions API with the model $model...")
    println("Generated three names to call my pet squirrel:")
    openAI.createCompletion(createCompletionRequest).choices.forEach { println(it.text) }
    println("=====================================================\n")

    // Edits API
    val createEditRequest = CreateEditRequest(
        model = "text-davinci-edit-001",
        input = "I can't read good anymore",
        instruction = "Fix the grammar in the sentence",
        temperature = 0.8
    )

    println("Calling /edits API to fix the grammar of the following sentence with the model text-davinci-edit-001...")
    println("Input:\nI can't read good\nOutput:")
    openAI.createEdit(createEditRequest).choices.forEach { println(it.text) }
    println("=====================================================\n")

    // Embeddings API
    val embeddingsInput = "The quick brown fox jumps over the lazy dog"
    val createEmbeddingsRequest = CreateEmbeddingsRequest(
        model = "text-embedding-ada-002",
        input = listOf(embeddingsInput)
    )

    println("Calling the /embeddings API for the following sentence with the model text-embedding-ada-002...")
    println("Input:\n$embeddingsInput\nOutput:")
    openAI.createEmbeddings(createEmbeddingsRequest).data.forEach { println(it.embedding.slice(0..10)) }
    println("=====================================================\n")
}
