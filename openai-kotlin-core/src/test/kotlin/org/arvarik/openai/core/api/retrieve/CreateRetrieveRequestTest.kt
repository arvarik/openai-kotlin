package org.arvarik.openai.core.api.retrieve

import kotlinx.serialization.serializer
import org.arvarik.openai.core.api.common.DataClassSerializationCommonTest

val createRetrieveRequest = CreateRetrieveRequest(
    model = "text-ada-001"
)

/**
 * Test fixture for [CreateRetrieveRequest]
 */
class CreateRetrieveRequestTest : DataClassSerializationCommonTest<CreateRetrieveRequest>(
    serializer(),
    createRetrieveRequest,
    expectedSerializedString = """{"model":"text-ada-001"}""",
    invalidSerializedString = """{"models":text-ada-001}"""
)
