package org.arvarik.openai.core.api.edits

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.arvarik.openai.core.api.Usage

/**
 * Test fixture for [CreateEditResponse]
 */
class CreateEditResponseTest : BehaviorSpec({

    given("CreateEditResponse instance") {
        val createEditResponse = CreateEditResponse(
            `object` = "Test object",
            created = 100000000L,
            choices = listOf(
                Edit(
                    text = "Test text 1",
                    index = 0
                ),
                Edit(
                    text = "Test text 2",
                    index = 1
                )
            ),
            usage = Usage(
                promptTokens = 100,
                completionTokens = 101,
                totalTokens = 201
            )
        )

        `when`("createEditResponse is serialized") {
            val createEditResponseSerialized = Json.Default.encodeToString(createEditResponse)

            then("returns correct serialization") {
                val expectedSerializedString = """{"object":"Test object","created":100000000,"choices":[{"text":"Test text 1","index":0},{"text":"Test text 2","index":1}],"usage":{"prompt_tokens":100,"completion_tokens":101,"total_tokens":201}}"""

                createEditResponseSerialized shouldBe expectedSerializedString
            }

            and("createEditResponse string is deserialized") {
                val createEditResponseDeserialized = Json.Default
                    .decodeFromString<CreateEditResponse>(createEditResponseSerialized)

                then("returns correct createEditResponse object deserialized") {
                    createEditResponseDeserialized shouldBe createEditResponse
                }
            }
        }
    }

    given("Invalid serialized string") {
        val invalidSerializedString = """{"object":"Test object","created":100000000,"choices":[{"text":"Test text 1","index":0},{"text":"Test text 2","index":1}],"usage":{"promptTokens":100,"completionTokens":101,"totalTokens":201}}"""

        `when`("createEditResponse string is deserialized") {
            then("throw exception") {
                shouldThrow<RuntimeException> {
                    Json.decodeFromString<CreateEditResponse>(invalidSerializedString)
                }
            }
        }
    }
})
