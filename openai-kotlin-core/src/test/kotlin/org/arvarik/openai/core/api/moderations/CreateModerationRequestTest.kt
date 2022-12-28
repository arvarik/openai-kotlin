package org.arvarik.openai.core.api.moderations

import kotlinx.serialization.serializer
import org.arvarik.openai.core.api.common.DataClassSerializationCommonTest

val createModerationRequest = CreateModerationRequest(input = listOf("Test input"))

/**
 * Test fixture for [CreateModerationRequest]
 */
class CreateModerationRequestTest : DataClassSerializationCommonTest<CreateModerationRequest>(
    serializer(),
    createModerationRequest,
    expectedSerializedString = """{"input":["Test input"]}""",
    invalidSerializedString = """{"inputs":["Test input"]}"""
)
