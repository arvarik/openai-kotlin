package org.arvarik.openai.core.api.models.listModels

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
            `object` = "list",
            data = listOf(
                Model(
                    id = "babbage",
                    object = "model",
                    created = 1649358449,
                    ownedBy = "openai",
                    permission = listOf(
                        Permission(
                            id = "modelperm-49FUp5v084tBB49tC4z8LPH5",
                            object = "model_permission",
                            created = 1669085501,
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
                    root = "babbage",
                    parent = null
                )                
            )
        )

        `when`("listModelsResponse is serialized") {
            val listModelsResponseSerialized = Json.Default.encodeToString(listModelsResponse)

            then("returns correct serialization") {
                val expectedSerializedString = """{"object": "list","data": [{"id": "babbage","object": "model","created": 1649358449,"owned_by": "openai","permission":[{"id": "modelperm-49FUp5v084tBB49tC4z8LPH5","object": "model_permission","created": 1669085501,"allow_create_engine": false,"allow_sampling": true,"allow_logprobs": true,"allow_search_indices": false,"allow_view": true,"allow_fine_tuning": false,"organization": "*","group": null,"is_blocking": false}],"root": "babbage","parent": null}]}"""

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
        val invalidSerializedString = """{"object": "list","data": [{"id": "babbage","object": "model","created": 1649358449,"ownedBy": "openai","permission":[{"id": "modelperm-49FUp5v084tBB49tC4z8LPH5","object": "modelPermission","created": 1669085501,"allowCreateEngine": false,"allowSampling": true,"allowLogprobs": true,"allowSearchIndices": false,"allowView": true,"allowFineTuning": false,"organization": "*","group": null,"isBlocking": false}],"root": "babbage","parent": null}]}"""

        `when`("listModelsResponse string is deserialized") {
            then("throw exception") {
                shouldThrow<RuntimeException> {
                    Json.decodeFromString<ListModelsResponse>(invalidSerializedString)
                }
            }
        }
    }
})
