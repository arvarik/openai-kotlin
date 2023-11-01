package org.arvarik.openai.core.api.retrieve

import kotlinx.serialization.serializer
import org.arvarik.openai.core.api.common.DataClassSerializationCommonTest
import org.arvarik.openai.core.api.models.RetrieveModelRequest

val retrieveModelRequest =
    RetrieveModelRequest(
        model = "text-ada-001",
    )

/**
 * Test fixture for [RetrieveModelRequest]
 */
class CreateRetrieveRequestTest : DataClassSerializationCommonTest<RetrieveModelRequest>(
    serializer(),
    retrieveModelRequest,
    expectedSerializedString = """{"model":"text-ada-001"}""",
    invalidSerializedString = """{"models":text-ada-001}""",
)
