package org.arvarik.openai.core.api

/**
 * An enum representing a GPT-3 Language Model
 *
 * Documentation: https://beta.openai.com/docs/models/gpt-3
 *
 * @param modelName the explicit name of the GPT-3 model
 * @param maxTokens the maximum number of tokens this model can process in one prompt
 */
enum class GPT3Model(val modelName: String, val maxTokens: Int) {
    ADA("text-ada-001", 2048),

    BABBAGE("text-babbage-001", 2048),

    CURIE("text-curie-001", 2048),

    DAVINCI("text-davinci-003", 4000)
}

/**
 * An enum representing a GPT-3 Language Model
 *
 * Documentation: https://beta.openai.com/docs/models/codex
 *
 * @param modelName the explicit name of the codex model
 * @param maxTokens the maximum number of tokens this model can process in one prompt
 */
enum class CodexModel(val modelName: String, val maxTokens: Int) {
    CUSHMAN("code-cushman-001", 2048),

    DAVINCI("text-davinci-003", 8000)
}
