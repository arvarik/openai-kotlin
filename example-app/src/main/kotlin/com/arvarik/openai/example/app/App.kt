package com.arvarik.openai.example.app

import kotlinx.coroutines.runBlocking
import org.arvarik.openai.client.OpenAIClient
import org.arvarik.openai.client.OpenAIClientConfig
import org.arvarik.openai.core.api.GPT3Model
import org.arvarik.openai.core.api.completions.CreateCompletionRequest
import org.arvarik.openai.core.api.edits.CreateEditRequest

fun main() = runBlocking {
    val openaiApiKey = System.getenv("OPENAI_API_KEY")
    val token = requireNotNull(openaiApiKey) { "ERROR: OPENAI_API_KEY env variable not set" }
    val openAI = OpenAIClient(OpenAIClientConfig(token))
    val model = GPT3Model.DAVINCI.modelName

    // List Models API
    println("Calling /models API...")
    openAI.listModels().data.forEach {
        println("id: " + it.id)
        println("object: " + it.`object`)
        println("owned_by: " + it.owned_by)
        it.permission.forEach {
            println("permission: id: " + it.id)
        }
        println("-----------------------------------------------------\n")
    }
    println("=====================================================\n")

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
    val input = "I can't read good anymore"
    val createEditRequest = CreateEditRequest(
        model = "text-davinci-edit-001",
        input = input,
        instruction = "Fix the grammar in the sentence",
        temperature = 0.8
    )

    println("Calling /edits API to fix the grammar of the following sentence with the model text-davinci-edit-001...")
    println("Input:\n" + input + "\nOutput:")
    openAI.createEdit(createEditRequest).choices.forEach { println(it.text) }
    println("=====================================================\n")
}
