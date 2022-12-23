package org.arvarik.openai.core.api

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Test fixture for [Usage]
 */
class UsageTest : BehaviorSpec({

    given("Usage instance") {
        val usage = Usage(
            promptTokens = 100,
            completionTokens = 101,
            totalTokens = 201
        )

        `when`("usage is serialized") {
            val usageSerialized = Json.Default.encodeToString(usage)

            then("returns correct serialization") {
                val expectedSerializedString = """{"prompt_tokens":100,"completion_tokens":101,"total_tokens":201}"""

                usageSerialized shouldBe expectedSerializedString
            }

            and("usage is deserialized") {
                val usageDeserialized = Json.Default.decodeFromString<Usage>(usageSerialized)

                then ("returns correct usage object deserialized") {
                    usageDeserialized shouldBe usage
                }
            }
        }
    }
})
