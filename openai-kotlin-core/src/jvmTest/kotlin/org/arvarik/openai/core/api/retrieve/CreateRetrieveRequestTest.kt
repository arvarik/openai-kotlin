package org.arvarik.openai.core.api.retrieve

import kotlinx.serialization.serializer
import org.arvarik.openai.core.api.common.DataClassSerializationCommonTest

val createRetrieveModelRequest = CreateRetrieveModelRequest(
    model = "text-ada-001"
)

/**
 * Test fixture for [CreateRetrieveModelRequest]
 */
class CreateRetrieveRequestTest : DataClassSerializationCommonTest<CreateRetrieveModelRequest>(
    serializer(),
    createRetrieveModelRequest,
    expectedSerializedString = """{"model":"text-ada-001"}""",
    invalidSerializedString = """{"models":text-ada-001}"""
)
