package com.arvarik.openai.example.app

import kotlinx.coroutines.runBlocking
import org.arvarik.openai.client.OpenAIClient
import org.arvarik.openai.client.OpenAIClientConfig
import org.arvarik.openai.core.api.GPT3Model
import org.arvarik.openai.core.api.completions.CreateCompletionRequest

fun main() = runBlocking {
    val openaiApiKey = System.getenv("OPENAI_API_KEY")
    val token = requireNotNull(openaiApiKey) { "ERROR: OPENAI_API_KEY env variable not set" }

    val openAI = OpenAIClient(OpenAIClientConfig(token))

    val createCompletionRequest = CreateCompletionRequest(
        model = GPT3Model.DAVINCI.modelName,
        prompt = "Give me 3 names for my pet squirrel",
        maxTokens = 20,
        temperature = 0.7
    )

    println("Calling /completions API with the model ${GPT3Model.DAVINCI.modelName}...")
    println("Generated three names to call my pet squirrel:")
    openAI.createCompletion(createCompletionRequest).choices.forEach { println(it.text) }
}