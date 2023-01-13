package org.arvarik.openai.core.api.models

import kotlinx.serialization.serializer
import org.arvarik.openai.core.api.common.DataClassSerializationCommonTest

/**
 * Test fixture for [ListModelsResponse]
 */

val listModelsResponse = ListModelsResponse(
    `object` = "list",
    data = listOf(
        Model(
            id = "babbage",
            `object` = "model",
            created = 1669085501,
            ownedBy = "openai",
            permission = listOf(
                ListPermission(
                    id = "modelperm-49FUp5v084tBB49tC4z8LPH5",
                    `object` = "model_permission",
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

class ListsModelsResponseTest : DataClassSerializationCommonTest<ListModelsResponse>(
    serializer(),
    listModelsResponse,
    expectedSerializedString = """{"object": "list","data": [{"id": "babbage","object": "model","created": 1669085501,"owned_by": "openai","permission":[{"id": "modelperm-49FUp5v084tBB49tC4z8LPH5","object": "model_permission","created": 1669085501,"allow_create_engine": false,"allow_sampling": true,"allow_logprobs": true,"allow_search_indices": false,"allow_view": true,"allow_fine_tuning": false,"organization": "*","is_blocking": false}],"root": "babbage"}]}""",
    invalidSerializedString = """{"object": "list","data": [{"id": "babbage","object": "model","created": 1649358449,"ownedBy": "openai","permission":[{"id": "modelperm-49FUp5v084tBB49tC4z8LPH5","object": "modelPermission","created": 1669085501,"allowCreateEngine": false,"allowSampling": true,"allowLogprobs": true,"allowSearchIndices": false,"allowView": true,"allowFineTuning": false,"organization": "*","isBlocking": false}],"root": "babbage"}]}"""
)
