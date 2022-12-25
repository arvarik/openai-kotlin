package org.arvarik.openai.core.api.embeddings

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.arvarik.openai.core.api.GPT3Model

/**
 * Test fixture for [CreateEmbeddingsRequest]
 */
class CreateEmbeddingsRequestTest : BehaviorSpec({

    given("CreateEmbeddingsRequest instance") {
        val createEmbeddingsRequest = CreateEmbeddingsRequest(
            model = GPT3Model.ADA.modelName,
            input = listOf("Test input 1", "Test input 2")
        )

        `when`("createEmbeddingsRequest is serialized") {
            val createEmbeddingsRequestSerialized = Json.Default.encodeToString(createEmbeddingsRequest)

            then("return correct serialization") {
                val expectedSerializedString = """{"model":"text-ada-001","input":["Test input 1","Test input 2"]}"""

                createEmbeddingsRequestSerialized shouldBe expectedSerializedString
            }

            and("createEmbeddingsRequest string is deserialized") {
                val createEmbeddingsRequestDeserialized = Json.Default
                    .decodeFromString<CreateEmbeddingsRequest>(createEmbeddingsRequestSerialized)

                then("returns correct createEmbeddingsRequest object deserialized") {
                    createEmbeddingsRequestDeserialized shouldBe createEmbeddingsRequest
                }
            }
        }
    }

    given("Invalid serialized string") {
        val invalidSerializedString = """{"models":"text-ada-001","input":["Test input 1","Test input 2"]}"""

        `when`("createEmbeddingsRequest string is deserialized") {
            then("throw exception") {
                shouldThrow<RuntimeException> {
                    Json.decodeFromString<CreateEmbeddingsRequest>(invalidSerializedString)
                }
            }
        }
    }
})
