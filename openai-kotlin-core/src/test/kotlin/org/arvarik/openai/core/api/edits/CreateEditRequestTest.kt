package org.arvarik.openai.core.api.edits

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.arvarik.openai.core.api.GPT3Model

/**
 * Test fixture for [CreateEditRequest]
 */
class CreateEditRequestTest : BehaviorSpec({

    given("CreateEditRequest instance") {
        val createEditRequest = CreateEditRequest(
            model = GPT3Model.DAVINCI.modelName,
            input = "Test input",
            instruction = "Test instruction",
            n = 2,
            temperature = 0.6,
            topP = 0.4
        )

        `when`("createEditRequest is serialized") {
            val createEditRequestSerialized = Json.Default.encodeToString(createEditRequest)

            then("returns correct serialization") {
                val expectedSerializedString = """{"model":"text-davinci-003","input":"Test input","instruction":"Test instruction","n":2,"temperature":0.6,"top_p":0.4}"""

                createEditRequestSerialized shouldBe expectedSerializedString
            }

            and("createEditRequest string is deserialized") {
                val createEditRequestDeserialized = Json.Default
                    .decodeFromString<CreateEditRequest>(createEditRequestSerialized)

                then("returns correct createEditRequest object deserialized") {
                    createEditRequestDeserialized shouldBe createEditRequest
                }
            }
        }
    }

    given("Invalid serialized string") {
        val invalidSerializedString = """{"model":"text-davinci-003","input":"Test input","instruction":"Test instruction","n":2,"temperature":0.6,"topP":0.4}"""

        `when`("createEditRequest string is deserialized") {
            then("throw exception") {
                shouldThrow<RuntimeException> {
                    Json.decodeFromString<CreateEditRequest>(invalidSerializedString)
                }
            }
        }
    }
})
