package com.arvarik.openai.example.app

import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.arvarik.openai.client.HttpTimeout
import org.arvarik.openai.client.OpenAIClient
import org.arvarik.openai.client.OpenAIClientConfig
import org.arvarik.openai.core.api.GPT3Model
import org.arvarik.openai.core.api.completions.CreateCompletionRequest
import org.arvarik.openai.core.api.edits.CreateEditRequest
import org.arvarik.openai.core.api.embeddings.CreateEmbeddingsRequest
import org.arvarik.openai.core.api.images.CreateImageEditRequest
import org.arvarik.openai.core.api.images.CreateImageRequest
import org.arvarik.openai.core.api.images.CreateImageVariationRequest
import org.arvarik.openai.core.api.moderations.CreateModerationRequest
import org.arvarik.openai.core.api.retrieve.CreateRetrieveRequest
import java.time.Duration
import kotlin.reflect.KSuspendFunction1
import kotlin.system.measureTimeMillis

fun main() = runBlocking {
    val token = requireNotNull(System.getenv("OPENAI_API_KEY")) { "ERROR: OPENAI_API_KEY env variable not set" }
    val config = OpenAIClientConfig(
        token,
        HttpTimeout(request = Duration.ofSeconds(60L))
    )
    val openAI = OpenAIClient(config)

    val time = callOpenAIApis(
        // Completions API //
        ::completionsApiExample,
        // Models APIs //
        // (1) List Models
        ::listModelsApiExample,
        // Edits API //
        ::editsApiExample,
        // Embeddings API //
        ::embeddingsApiExample,
        // Models APIs //
        // (2) Retrieve Models
        ::retrieveModelApiExample,
        // Moderations API //
        ::moderationsApiExample,
        // Images APIs //
        // (1) Create Image
        ::createImageApiExample,
        // (2) Create Image Edit
        ::createImageEditApiExample,
        // (3) Create Image Variation
        ::createImageVariationApiExample,
        openAI = openAI
    )

    println("Finished example-app OpenAI API executions in ${time}ms")
}

suspend fun retrieveModelApiExample(openAI: OpenAIClient) {
    val model = "text-davinci-edit-001"
    val createRetrieveRequest = CreateRetrieveRequest(
        model = model
    )

    println("Calling retrieve models API")
    val retrieveModel = openAI.retrieveModel(createRetrieveRequest)
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
}

suspend fun completionsApiExample(openAI: OpenAIClient) {
    val model = GPT3Model.DAVINCI.modelName
    val prompt = "Give me 3 names for my pet squirrel"
    val createCompletionResponse = openAI.createCompletion(
        CreateCompletionRequest(
            model = model,
            prompt = prompt,
            maxTokens = 20,
            temperature = 0.7
        )
    )

    val output = createCompletionResponse.choices.joinToString("\n") { it.text }.trim()

    printOutput(
        "CreateCompletion (/completions)",
        model,
        prompt,
        "Creates a completion for the provided prompt",
        output
    )
}

suspend fun listModelsApiExample(openAI: OpenAIClient) {
    val listModelsResponse = openAI.listModels()
    val model1 = listModelsResponse.data[0]
    val permission1 = model1.permission[0]
    val output = listOf(
        "id: ${model1.id}",
        "object: ${model1.`objectType`}",
        "owned_by: ${model1.ownedBy}",
        "permission: id: ${permission1.id}",
        "permission: object: ${permission1.`objectType`}",
        "permission: created: ${permission1.created}",
        "permission: allow_create_engine: ${permission1.allowCreateEngine}",
        "permission: allow_sampling: ${permission1.allowSampling}",
        "permission: allow_logprobs: ${permission1.allowLogprobs}",
        "permission: allow_search_indices: ${permission1.allowSearchIndices}",
        "permission: allow_view: ${permission1.allowView}",
        "permission: allow_fine_tuning: ${permission1.allowFineTuning}",
        "permission: organization: ${permission1.organization}",
        "permission: group: ${permission1.group}",
        "permission: is_blocking: ${permission1.isBlocking}",
        "root: ${model1.root}",
        "parent: ${model1.parent}"
    ).joinToString("\n")

    printOutput(
        "ListModels (/models)",
        "N/A (lists all models)",
        "N/A (lists all models)",
        "List currently available models, along with basic information about each one such as the owner and availability. For this example, we just show data associated with the first model in the response",
        output
    )
}

suspend fun editsApiExample(openAI: OpenAIClient) {
    val model = "text-davinci-edit-001"
    val input = "I can't read good anymore"
    val instruction = "Fix the grammar in the sentence"
    val createEditResponse = openAI.createEdit(
        CreateEditRequest(
            model = model,
            input = input,
            instruction = instruction,
            temperature = 0.8
        )
    )

    val output = createEditResponse.choices.joinToString("\n") { it.text }

    printOutput(
        api = "CreateEdit (/edits)",
        model,
        input,
        task = "Apply the following instruction to the input text - \"Fix the grammar in the sentence\"",
        output
    )
}

suspend fun embeddingsApiExample(openAI: OpenAIClient) {
    val model = "text-embedding-ada-002"
    val embeddingsInput = "The quick brown fox jumps over the lazy dog"
    val createEmbeddingsResponse = openAI.createEmbeddings(
        CreateEmbeddingsRequest(
            model = model,
            input = listOf(embeddingsInput)
        )
    )

    val output = createEmbeddingsResponse.data.joinToString("\n") {
        it.embedding.slice(0..5).toString()
    }

    printOutput(
        api = "CreateEmbeddings (/embeddings)",
        model,
        embeddingsInput,
        task = "Create an embedding vector representing the input text (first 5 vector embeddings)",
        output
    )
}

suspend fun moderationsApiExample(openAI: OpenAIClient) {
    val moderationInput = "I wanna die"
    val createModerationRequest = CreateModerationRequest(input = listOf(moderationInput))
    val createModerationResponse = openAI.createModeration(createModerationRequest)

    val output = createModerationResponse.results.joinToString("\n") {
        "Flagged - ${it.flagged}\n${it.categories}\n${it.categoryScores}"
    }

    printOutput(
        api = "CreateModeration (/moderations)",
        model = "text-moderation-latest",
        moderationInput,
        task = "Classify if the input text violates OpenAI's Content Policy",
        output
    )
}

suspend fun createImageApiExample(openAI: OpenAIClient) {
    val prompt = "Three friends eating lunch by the pool"
    val createImageResponse = openAI.createImage(
        CreateImageRequest(
            prompt = prompt,
            size = "256x256"
        )
    )

    val output = createImageResponse.data.joinToString("\n") { it.url }

    printOutput(
        api = "CreateImage (/images/generations)",
        model = "DALL-E",
        prompt,
        task = "Create an image given input",
        output
    )
}

suspend fun createImageEditApiExample(openAI: OpenAIClient) {
    val image = "./images/taxi.png"
    val prompt = "Make this image into black and white"
    val createImageEditResponse = openAI.createImageEdit(
        CreateImageEditRequest(
            image = image,
            prompt = prompt
        )
    )

    val output = createImageEditResponse.data.joinToString("\n") { it.url }

    printOutput(
        api = "CreateImageEdit (/images/edits)",
        model = "DALL-E",
        prompt,
        task = "Edit the given image with the input instruction",
        output
    )
}

suspend fun createImageVariationApiExample(openAI: OpenAIClient) {
    val image = "./images/taxi.png"
    val createImageVariationResponse = openAI.createImageVariation(CreateImageVariationRequest(image = image))

    val output = createImageVariationResponse.data.joinToString("\n") { it.url }

    printOutput(
        api = "CreateImageVariation (/images/variations)",
        model = "DALL-E",
        input = "Input is a generic image of a taxi - found under /images/taxi.png",
        task = "Create a variation of the input image",
        output
    )
}

private suspend fun callOpenAIApis(
    vararg jobs: KSuspendFunction1<OpenAIClient, Unit>,
    openAI: OpenAIClient
): Long = runBlocking {
    measureTimeMillis { jobs.map { job -> launch { job.invoke(openAI) } }.joinAll() }
}

private fun printOutput(api: String, model: String, input: String, task: String, output: String) {
    println("Calling the $api API with the model $model...")
    println("Input: $input\nTask: $task\nOutput:\n$output".trimEnd())
    println("====================================================================================================\n")
}
