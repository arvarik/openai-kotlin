package org.arvarik.openai.core.api

import java.time.LocalDate

/**
 * An enum representing a GPT-3 Language Model
 *
 * Documentation: https://beta.openai.com/docs/models/gpt-3
 *
 * @param modelName the explicit name of the GPT-3 model
 * @param maxTokens the maximum number of tokens this model can process in one prompt
 * @param trainingDataDate the latest date of data used to train the model (granular to month)
 */
enum class GPT3Model(val modelName: String, val maxTokens: Int, val trainingDataDate: LocalDate) {
    ADA("text-ada-001", 2048, LocalDate.of(2019, 10, 1)),

    BABBAGE("text-babbage-001", 2048, LocalDate.of(2019, 10, 1)),

    CURIE("text-curie-001", 2048, LocalDate.of(2019, 10, 1)),

    DAVINCI("text-davinci-003", 4000, LocalDate.of(2021, 6, 1))
}

/**
 * An enum representing a GPT-3 Language Model
 *
 * Documentation: https://beta.openai.com/docs/models/codex
 *
 * @param modelName the explicit name of the codex model
 * @param maxTokens the maximum number of tokens this model can process in one prompt
 * @param trainingDataDate the latest date of data used to train the model (granular to month)
 */
enum class CodexModel(val modelName: String, val maxTokens: Int, val trainingDataDate: LocalDate) {
    CUSHMAN("code-cushman-001", 2048, LocalDate.of(2021, 6, 1)),

    DAVINCI("text-davinci-003", 8000, LocalDate.of(2021, 6, 1))
}