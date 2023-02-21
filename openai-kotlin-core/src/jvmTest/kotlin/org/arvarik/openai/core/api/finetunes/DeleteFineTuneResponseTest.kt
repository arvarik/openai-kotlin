package org.arvarik.openai.core.api.finetunes

import kotlinx.serialization.serializer
import org.arvarik.openai.core.api.common.DataClassSerializationCommonTest

private val deleteFineTuneResponse = DeleteFineTuneResponse(
    modelId = "curie:ft-acmeco-2021-03-03-21-44-20",
    objectType = "model",
    deleted = true
)

/**
 * Test fixture for [DeleteFineTuneResponse].
 */
class DeleteFineTuneResponseTest : DataClassSerializationCommonTest<DeleteFineTuneResponse>(
    serializer(),
    deleteFineTuneResponse,
    expectedSerializedString = """
        {
            "id": "curie:ft-acmeco-2021-03-03-21-44-20",
            "object": "model",
            "deleted": true
        }
    """.trimIndent(),
    invalidSerializedString = """
        {
            "id": "curie:ft-acmeco-2021-03-03-21-44-20",
            "object": "model",
        }
    """.trimIndent()
)
