package org.arvarik.openai.core.api

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

/**
 * Test fixture for [GPT3Model] and [CodexModel]
 */
class ModelsTest : BehaviorSpec({

    given("gpt-3 model enum values") {
        val gpt3Models = GPT3Model.values()

        `when`("access model name") {
            val modelNames = gpt3Models.map { it.modelName }

            then("returns correct model names") {
                val expectedModelNames = listOf(
                    "text-ada-001",
                    "text-babbage-001",
                    "text-curie-001",
                    "text-davinci-003"
                )
                modelNames shouldBe expectedModelNames
            }
        }

        `when`("access max tokens") {
            val maxTokens = gpt3Models.map { it.maxTokens }

            then("returns correct max tokens") {
                val expectedMaxTokens = listOf(
                    2048,
                    2048,
                    2048,
                    4000
                )
                maxTokens shouldBe expectedMaxTokens
            }
        }
    }

    given("codex model enum values") {
        val codexModels = CodexModel.values()

        `when`("access model name") {
            val modelNames = codexModels.map { it.modelName }

            then("returns correct model names") {
                val expectedModelNames = listOf(
                    "code-cushman-001",
                    "text-davinci-003"
                )
                modelNames shouldBe expectedModelNames
            }
        }

        `when`("access max tokens") {
            val maxTokens = codexModels.map { it.maxTokens }

            then("returns correct max tokens") {
                val expectedMaxTokens = listOf(
                    2048,
                    8000
                )
                maxTokens shouldBe expectedMaxTokens
            }
        }
    }
})
