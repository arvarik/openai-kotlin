package com.arvarik.openai.example.app

import kotlinx.coroutines.runBlocking
import org.arvarik.openai.client.OpenAIClient
import org.arvarik.openai.client.OpenAIClientConfig
import org.arvarik.openai.core.api.GPT3Model
import org.arvarik.openai.core.api.completions.CreateCompletionRequest
import org.arvarik.openai.core.api.edits.CreateEditRequest
import org.arvarik.openai.core.api.embeddings.CreateEmbeddingsRequest
import org.arvarik.openai.core.api.moderations.CreateModerationRequest

fun main() = runBlocking {
    val openaiApiKey = System.getenv("OPENAI_API_KEY")
    val token = requireNotNull(openaiApiKey) { "ERROR: OPENAI_API_KEY env variable not set" }
    val openAI = OpenAIClient(OpenAIClientConfig(token))

    // Completions API
    completionsApiExample(openAI)

    // Edits API
    editsApiExample(openAI)

    // Embeddings API
    embeddingsApiExample(openAI)

    // Moderations API
    moderationsApiExample(openAI)
}

suspend fun completionsApiExample(openAI: OpenAIClient) {
    val model = GPT3Model.DAVINCI.modelName
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
}

suspend fun editsApiExample(openAI: OpenAIClient) {
    val model = "text-davinci-edit-001"
    val input = "I can't read good anymore"
    val createEditRequest = CreateEditRequest(
        model = model,
        input = input,
        instruction = "Fix the grammar in the sentence",
        temperature = 0.8
    )

    println("Calling /edits API to fix the grammar of the following sentence with the model $model...")
    println("Input:\n$input\nOutput:")
    openAI.createEdit(createEditRequest).choices.forEach { println(it.text) }
    println("=====================================================\n")
}

suspend fun embeddingsApiExample(openAI: OpenAIClient) {
    val model = "text-embedding-ada-002"
    val embeddingsInput = "The quick brown fox jumps over the lazy dog"
    val createEmbeddingsRequest = CreateEmbeddingsRequest(
        model = model,
        input = listOf(embeddingsInput)
    )

    println("Calling the /embeddings API for the following sentence with the model $model...")
    println("Input:\n$embeddingsInput\nOutput (First 5 vector embeddings):")
    openAI.createEmbeddings(createEmbeddingsRequest).data.forEach { println(it.embedding.slice(0..5)) }
    println("=====================================================\n")
}

suspend fun moderationsApiExample(openAI: OpenAIClient) {
    val moderationsInput = "I wanna die"
    val createModerationRequest = CreateModerationRequest(input = listOf(moderationsInput))

    println("Calling the /moderations API for the following sentence with the model text-moderation-latest...")
    println("Input:\n$moderationsInput\nOutput:")
    openAI.createModeration(createModerationRequest).results.forEach {
        println("Flagged - ${it.flagged}")
        println("${it.categories}\n${it.categoryScores}")
    }
    println("=====================================================\n")
}
