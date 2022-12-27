package org.arvarik.openai.core.api.embeddings

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.arvarik.openai.core.api.GPT3Model
import org.arvarik.openai.core.api.Usage

/**
 * Test fixture for [CreateEmbeddingsResponse]
 */
class CreateEmbeddingsResponseTest : BehaviorSpec({

    given("CreateEmbeddingsResponse instance") {
        val createEmbeddingsResponse = CreateEmbeddingsResponse(
            `object` = "Test object",
            model = GPT3Model.ADA.modelName,
            data = listOf(
                Embedding(
                    `object` = "Test embedding 1",
                    embedding = listOf(0.0, 1.0, 2.0),
                    index = 0
                ),
                Embedding(
                    `object` = "Test embedding 2",
                    embedding = listOf(3.0, 4.0, 5.0),
                    index = 0
                )
            ),
            usage = Usage(
                promptTokens = 100,
                totalTokens = 201
            )
        )

        `when`("createEmbeddingsResponse is serialized") {
            val createEmbeddingsResponseSerialized = Json.Default.encodeToString(createEmbeddingsResponse)

            then("returns correct serialization") {
                val expectedSerializedString = """{"object":"Test object","data":[{"object":"Test embedding 1","embedding":[0.0,1.0,2.0],"index":0},{"object":"Test embedding 2","embedding":[3.0,4.0,5.0],"index":0}],"model":"text-ada-001","usage":{"prompt_tokens":100,"total_tokens":201}}"""

                createEmbeddingsResponseSerialized shouldBe expectedSerializedString
            }

            and("createEmbeddingsResponse string is deserialized") {
                val createEmbeddingsResponseDeserialized = Json.Default
                    .decodeFromString<CreateEmbeddingsResponse>(createEmbeddingsResponseSerialized)

                then("returns correct createEmbeddingsResponse object deserialized") {
                    createEmbeddingsResponseDeserialized shouldBe createEmbeddingsResponse
                }
            }
        }
    }

    given("Invalid serialized string") {
        val invalidSerializedString = """{"object":"Test object","data":[{"object":"Test embedding 1","embedding":[0.0,1.0,2.0],"index":0},{"object":"Test embedding 2","embedding":[3.0,4.0,5.0],"index":0}],"model":"text-ada-001","usage":{"promptTokens":100,"totalTokens":201}}"""

        `when`("createEmbeddingsResponse string is deserialized") {
            then("throw exception") {
                shouldThrow<RuntimeException> {
                    Json.decodeFromString<CreateEmbeddingsResponse>(invalidSerializedString)
                }
            }
        }
    }
})
