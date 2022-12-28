package com.arvarik.openai.example.app

import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.arvarik.openai.client.OpenAIClient
import org.arvarik.openai.client.OpenAIClientConfig
import org.arvarik.openai.core.api.GPT3Model
import org.arvarik.openai.core.api.completions.CreateCompletionRequest
import org.arvarik.openai.core.api.edits.CreateEditRequest
import org.arvarik.openai.core.api.embeddings.CreateEmbeddingsRequest
import org.arvarik.openai.core.api.images.CreateImageRequest
import org.arvarik.openai.core.api.moderations.CreateModerationRequest
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val openaiApiKey = System.getenv("OPENAI_API_KEY")
    val token = requireNotNull(openaiApiKey) { "ERROR: OPENAI_API_KEY env variable not set" }
    val openAI = OpenAIClient(OpenAIClientConfig(token))

    val timeInMillis = measureTimeMillis {
        val jobs = listOf(
            // Completions API //
            launch { completionsApiExample(openAI) },
            // Edits API //
            launch { editsApiExample(openAI) },
            // Embeddings API //
            launch { embeddingsApiExample(openAI) },
            // Moderations API //
            launch { moderationsApiExample(openAI) },
            // Images APIs //
            // (1) Create Image
            launch { createImagesApiExample(openAI) }
        )

        jobs.joinAll()
    }

    println("Finished example-app OpenAI API executions in ${timeInMillis}ms")
}

suspend fun completionsApiExample(openAI: OpenAIClient) {
    val model = GPT3Model.DAVINCI.modelName
    val createCompletionRequest = CreateCompletionRequest(
        model = model,
        prompt = "Give me 3 names for my pet squirrel",
        maxTokens = 20,
        temperature = 0.7
    )
    val createCompletionResponse = openAI.createCompletion(createCompletionRequest)

    println("Calling /completions API with the model $model...")
    println("Generated three names to call my pet squirrel:")
    createCompletionResponse.choices.forEach { println(it.text) }
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
    val createEditResponse = openAI.createEdit(createEditRequest)

    println("Calling /edits API to fix the grammar of the following sentence with the model $model...")
    println("Input:\n$input\nOutput:")
    createEditResponse.choices.forEach { println(it.text) }
    println("=====================================================\n")
}

suspend fun embeddingsApiExample(openAI: OpenAIClient) {
    val model = "text-embedding-ada-002"
    val embeddingsInput = "The quick brown fox jumps over the lazy dog"
    val createEmbeddingsRequest = CreateEmbeddingsRequest(
        model = model,
        input = listOf(embeddingsInput)
    )
    val createEmbeddingsResponse = openAI.createEmbeddings(createEmbeddingsRequest)

    println("Calling the /embeddings API for the following sentence with the model $model...")
    println("Input:\n$embeddingsInput\nOutput (First 5 vector embeddings):")
    createEmbeddingsResponse.data.forEach { println(it.embedding.slice(0..5)) }
    println("=====================================================\n")
}

suspend fun moderationsApiExample(openAI: OpenAIClient) {
    val moderationsInput = "I wanna die"
    val createModerationRequest = CreateModerationRequest(input = listOf(moderationsInput))
    val createModerationResponse = openAI.createModeration(createModerationRequest)

    println("Calling the /moderations API for the following sentence with the model text-moderation-latest...")
    println("Input:\n$moderationsInput\nOutput:")
    createModerationResponse.results.forEach {
        println("Flagged - ${it.flagged}")
        println("${it.categories}\n${it.categoryScores}")
    }
    println("=====================================================\n")
}

suspend fun createImagesApiExample(openAI: OpenAIClient) {
    val prompt = "three friends eating lunch by the pool"
    val createImageRequest = CreateImageRequest(prompt = prompt)
    val createImageResponse = openAI.createImage(createImageRequest)

    println("Calling the /images/generations API to create an image of $prompt...\nOutput:")
    createImageResponse.data.forEach { println(it.url) }
    println("=====================================================\n")
}
