package com.arvarik.openai.example.js

import org.arvarik.openai.client.HttpTimeout
import org.arvarik.openai.client.OpenAIClient
import org.arvarik.openai.client.OpenAIClientConfig
import org.arvarik.openai.core.api.GPT3Model
import org.arvarik.openai.core.api.completions.CreateCompletionRequest
import kotlin.time.Duration.Companion.seconds

suspend fun main() {
    val token = requireNotNull(js("process.env.OPENAI_API_KEY").unsafeCast<String?>()) { "ERROR: OPENAI_API_KEY env variable not set" }
    val config = OpenAIClientConfig(
        token,
        HttpTimeout(request = 60.seconds)
    )
    val openAI = OpenAIClient(config)

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

private fun printOutput(api: String, model: String, input: String, task: String, output: String) {
    println("Calling the $api API with the model $model...")
    println("Input: $input\nTask: $task\nOutput:\n$output".trimEnd())
    println("====================================================================================================\n")
}
