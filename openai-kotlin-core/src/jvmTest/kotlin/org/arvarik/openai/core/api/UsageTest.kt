package org.arvarik.openai.core.api

import kotlinx.serialization.serializer
import org.arvarik.openai.core.api.common.DataClassSerializationCommonTest

val usage =
    Usage(
        promptTokens = 100,
        completionTokens = 101,
        totalTokens = 201,
    )

/**
 * Test fixture for [Usage]
 */
class UsageTest : DataClassSerializationCommonTest<Usage>(
    serializer(),
    usage,
    expectedSerializedString = """{"prompt_tokens":100,"completion_tokens":101,"total_tokens":201}""",
    invalidSerializedString = """{"promptTokens":100,"completionTokens":101,"totalTokens":201}""",
)
