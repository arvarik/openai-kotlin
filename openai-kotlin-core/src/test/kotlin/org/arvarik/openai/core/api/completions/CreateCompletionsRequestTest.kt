package org.arvarik.openai.core.api.completions

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.arvarik.openai.core.api.GPT3Model

/**
 * Test fixture for [CreateCompletionRequest]
 */
class CreateCompletionsRequestTest : BehaviorSpec({

    given("CreateCompletionRequest instance") {
        val createCompletionRequest = CreateCompletionRequest(
            model = GPT3Model.DAVINCI.modelName,
            prompt = "Test prompt",
            suffix = "suffix",
            maxTokens = 20,
            temperature = 0.5,
            topP = 0.1,
            presencePenalty = 1.0,
            bestOf = 15,
            logitBias = mapOf("1" to 25, "2" to 26)
        )

        `when`("createCompletionRequest is serialized") {
            val createCompletionRequestSerialized = Json.Default.encodeToString(createCompletionRequest)

            then("returns correct serialization") {
                val expectedSerializedString = """{"model":"text-davinci-003","prompt":"Test prompt","suffix":"suffix","max_tokens":20,"temperature":0.5,"top_p":0.1,"presence_penalty":1.0,"best_of":15,"logit_bias":{"1":25,"2":26}}"""

                createCompletionRequestSerialized shouldBe expectedSerializedString
            }

            and("createCompletionRequest string is deserialized") {
                val usageDeserialized = Json.Default
                    .decodeFromString<CreateCompletionRequest>(createCompletionRequestSerialized)

                then("returns correct usage object deserialized") {
                    usageDeserialized shouldBe createCompletionRequest
                }
            }
        }
    }

    given("Invalid serialized string") {
        val invalidSerializedString = """{"model":"text-davinci-003","prompt":"Test prompt","suffix":"suffix","maxTokens":20,"temperature":0.5,"top_p":0.1,"presencePenalty":1.0,"bestOf":15,"logitBias":{"1":25,"2":26}}"""

        `when`("usage string is deserialized") {
            then("throw exception") {
                shouldThrow<RuntimeException> {
                    Json.decodeFromString<CreateCompletionRequest>(invalidSerializedString)
                }
            }
        }
    }
})
