package org.arvarik.openai.core.api.finetunes

import kotlinx.serialization.serializer
import org.arvarik.openai.core.api.common.DataClassSerializationCommonTest

private val deleteFineTuneRequest = DeleteFineTuneRequest(
    modelId = "model_id"
)

/**
 * Test fixture for [DeleteFineTuneRequest].
 */
class DeleteFineTuneRequestTest : DataClassSerializationCommonTest<DeleteFineTuneRequest>(
    serializer(),
    deleteFineTuneRequest,
    expectedSerializedString = """
        {
            "model": "model_id"
        }
    """.trimIndent(),
    invalidSerializedString = """
        {
            "id": "model id"
        }
    """.trimIndent()
)
