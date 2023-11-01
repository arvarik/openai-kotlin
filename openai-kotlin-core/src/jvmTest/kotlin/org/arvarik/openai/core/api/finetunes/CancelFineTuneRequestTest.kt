package org.arvarik.openai.core.api.finetunes

import kotlinx.serialization.serializer
import org.arvarik.openai.core.api.common.DataClassSerializationCommonTest

private val cancelFineTuneRequest =
    CancelFineTuneRequest(
        fineTuneId = "fine-tune id",
    )

/**
 * Test fixture for [CancelFineTuneRequest].
 */
class CancelFineTuneRequestTest : DataClassSerializationCommonTest<CancelFineTuneRequest>(
    serializer(),
    cancelFineTuneRequest,
    expectedSerializedString =
        """
        {
            "fine_tune_id": "fine-tune id"
        }
        """.trimIndent(),
    invalidSerializedString =
        """
        {
            "id": "fine-tune id"
        }
        """.trimIndent(),
)
