package org.arvarik.openai.core.api.retrieve

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.arvarik.openai.core.api.GPT3Model
import org.arvarik.openai.core.api.Usage

/**
 * Test fixture for [ListModelsResponse]
 */
class ListModelsResponseTest : BehaviorSpec({

    given("ListModelsResponse instance") {
        val listModelsResponse = ListModelsResponse(
            id = "text-davinci-003",
            `object` = "model",
            created = 1669599635,
            ownedBy = "openai",
            permission = listOf(
                    Permission(
                        id = "modelperm-avGyoQNQYTDulwkeSa6n60ry",
                        `object` = "model_permission",
                        created = 1671910080,
                        allowCreateEngine = false,
                        allowSampling = true,
                        allowLogprobs = true,
                        allowSearchIndices = false,
                        allowView = true,
                        allowFineTuning = false,
                        organization = "*",
                        group = null,
                        isBlocking = false
                    )
                ),
                root = "text-davinci-003",
                parent = null
            )
        )

        `when`("listModelsResponse is serialized") {
            val listModelsResponseSerialized = Json.Default.encodeToString(listModelsResponse)

            then("returns correct serialization") {
                val expectedSerializedString = """{"id": "text-davinci-003","object": "model","created": 1669599635,"owned_by": "openai","permission":[{"id": "modelperm-avGyoQNQYTDulwkeSa6n60ry","object": "model_permission","created": 1671910080,"allow_create_engine": false,"allow_sampling": true,"allow_logprobs": true,"allow_search_indices": false,"allow_view": true,"allow_fine_tuning": false,"organization": "*","group": null,"is_blocking": false}],"root": "text-davinci-003","parent": null}]}"""

                listModelsResponseSerialized shouldBe expectedSerializedString
            }

            and("listModelsResponse string is deserialized") {
                val listModelsResponseDeserialized = Json.Default
                    .decodeFromString<ListModelsResponse>(listModelsResponseSerialized)

                then("returns correct listModelsResponse object deserialized") {
                    listModelsResponseDeserialized shouldBe listModelsResponse
                }
            }
        }
    }

    given("Invalid serialized string") {
        val invalidSerializedString = """{"id": "text-davinci-003","object": "model","created": 1669599635,"ownedBy": "openai","permission":[{"id": "modelperm-avGyoQNQYTDulwkeSa6n60ry","object": "modelPermission","created": 1671910080,"allowCreateEngine": false,"allowSampling": true,"allowLogprobs": true,"allowSearchIndices": false,"allowView": true,"allowFineTuning": false,"organization": "*","group": null,"isBlocking": false}],"root": "text-davinci-003","parent": null}]}"""

        `when`("listModelsResponse string is deserialized") {
            then("throw exception") {
                shouldThrow<RuntimeException> {
                    Json.decodeFromString<ListModelsResponse>(invalidSerializedString)
                }
            }
        }
    }
})