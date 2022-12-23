package org.arvarik.openai.core.api.completions

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.arvarik.openai.core.api.GPT3Model
import org.arvarik.openai.core.api.Usage

/**
 * Test fixture for [CreateCompletionResponse]
 */
class CreateCompletionResponseTest : BehaviorSpec({

    given("CreateCompletionResponse instance") {
        val createCompletionResponse = CreateCompletionResponse(
            id = "Test id",
            `object` = "Test object",
            created = 10000000L,
            model = GPT3Model.DAVINCI.modelName,
            choices = listOf(
                Completion(
                    text = "Test completion text",
                    index = 0,
                    logprobs = Logprobs(
                        tokens = listOf("Test", "token"),
                        tokenLogprobs = listOf(.2, .8),
                        topLogprobs = listOf(mapOf("token" to 0.8)),
                        textOffset = listOf(0)
                    ),
                    finishReason = "Test finish reason"
                )
            ),
            usage = Usage(
                promptTokens = 100,
                completionTokens = 101,
                totalTokens = 201
            )
        )

        `when`("createCompletionResponse is serialized") {
            val createCompletionResponseSerialized = Json.Default.encodeToString(createCompletionResponse)

            then("returns correct serialization") {
                val expectedSerializedString = """{"id":"Test id","object":"Test object","created":10000000,"model":"text-davinci-003","choices":[{"text":"Test completion text","index":0,"logprobs":{"tokens":["Test","token"],"token_logprobs":[0.2,0.8],"top_logprobs":[{"token":0.8}],"text_offset":[0]},"finish_reason":"Test finish reason"}],"usage":{"prompt_tokens":100,"completion_tokens":101,"total_tokens":201}}"""

                createCompletionResponseSerialized shouldBe expectedSerializedString
            }

            and("createCompletionResponse string is deserialized") {
                val createCompletionResponseDeserialized = Json.Default
                    .decodeFromString<CreateCompletionResponse>(createCompletionResponseSerialized)

                then("returns correct createCompletionResponse object deserialized") {
                    createCompletionResponseDeserialized shouldBe createCompletionResponse
                }
            }
        }
    }

    given("Invalid serialized string") {
        val invalidSerializedString = """{"id":"Test id","object":"Test object","created":10000000,"model":"text-davinci-003","choices":[{"text":"Test completion text","index":0,"logprobs":{"tokens":["Test","token"],"tokenLogprobs":[0.2,0.8],"topLogprobs":[{"token":0.8}],"textOffset":[0]},"finishReason":"Test finish reason"}],"usage":{"promptTokens":100,"completionTokens":101,"totalTokens":201}}"""

        `when`("createCompletionResponse string is deserialized") {
            then("throw exception") {
                shouldThrow<RuntimeException> {
                    Json.decodeFromString<CreateCompletionResponse>(invalidSerializedString)
                }
            }
        }
    }
})
