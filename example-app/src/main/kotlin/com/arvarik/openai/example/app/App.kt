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

    // List Models API
    println("Calling /models API and printing data on first model...")
    val model1 = openAI.listModels().data[0]
    println("id: ${model1.id}")
    println("object: ${model1.`object`}")
    println("owned_by: ${model1.ownedBy}")
    
    val permission1 = model1.permission[0] // printing first permission object
    println("permission: id: ${permission1.id}")
    println("permission: object: ${permission1.`object`}")
    println("permission: created: ${permission1.created}")
    println("permission: allow_create_engine: ${permission1.allowCreateEngine}")
    println("permission: allow_sampling: ${permission1.allowSampling}")
    println("permission: allow_logprobs: ${permission1.allowLogprobs}")
    println("permission: allow_search_indices: ${permission1.allowSearchIndices}")
    println("permission: allow_view: ${permission1.allowView}")
    println("permission: allow_fine_tuning: ${permission1.allowFineTuning}")
    println("permission: organization: ${permission1.organization}")
    println("permission: group: ${permission1.group}")
    println("permission: is_blocking: ${permission1.isBlocking}")

    println("root: ${model1.root}")
    println("parent: ${model1.parent}")
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
    println("Input: $input")
    openAI.createEdit(createEditRequest).choices.forEach { println("Output: ${it.text}") }
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
