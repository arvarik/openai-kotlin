package com.arvarik.openai.example.app

import kotlinx.coroutines.runBlocking
import org.arvarik.openai.client.OpenAIClient
import org.arvarik.openai.client.OpenAIClientConfig
import org.arvarik.openai.core.api.GPT3Model
import org.arvarik.openai.core.api.completions.CreateCompletionRequest
import org.arvarik.openai.core.api.edits.CreateEditRequest
import org.arvarik.openai.core.api.retrieve.CreateRetrieveRequest
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

    // Retrieve Models API

    val createRetrieveRequest = CreateRetrieveRequest(
        model = model
    )

    println("Calling retrieve models API")
    val retrieveModel = openAI.retrieveModels(createRetrieveRequest)
    println("id: ${retrieveModel.id}")
    println("object: ${retrieveModel.`object`}")
    println("owned_by: ${retrieveModel.ownedBy}")

    val permissionRetrieveModel = retrieveModel.permission[0] // printing first permission object
    println("permission: id: ${permissionRetrieveModel.id}")
    println("permission: object: ${permissionRetrieveModel.`object`}")
    println("permission: created: ${permissionRetrieveModel.created}")
    println("permission: allow_create_engine: ${permissionRetrieveModel.allowCreateEngine}")
    println("permission: allow_sampling: ${permissionRetrieveModel.allowSampling}")
    println("permission: allow_logprobs: ${permissionRetrieveModel.allowLogprobs}")
    println("permission: allow_search_indices: ${permissionRetrieveModel.allowSearchIndices}")
    println("permission: allow_view: ${permissionRetrieveModel.allowView}")
    println("permission: allow_fine_tuning: ${permissionRetrieveModel.allowFineTuning}")
    println("permission: organization: ${permissionRetrieveModel.organization}")
    println("permission: group: ${permissionRetrieveModel.group}")
    println("permission: is_blocking: ${permissionRetrieveModel.isBlocking}")

    println("root: ${retrieveModel.root}")
    println("parent: ${retrieveModel.parent}")

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
