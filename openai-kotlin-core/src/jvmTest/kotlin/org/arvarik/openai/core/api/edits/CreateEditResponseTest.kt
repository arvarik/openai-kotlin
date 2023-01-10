package org.arvarik.openai.core.api.edits

import kotlinx.serialization.serializer
import org.arvarik.openai.core.api.Usage
import org.arvarik.openai.core.api.common.DataClassSerializationCommonTest

val createEditResponse = CreateEditResponse(
    `object` = "Test object",
    created = 100000000L,
    choices = listOf(
        Edit(
            text = "Test text 1",
            index = 0
        ),
        Edit(
            text = "Test text 2",
            index = 1
        )
    ),
    usage = Usage(
        promptTokens = 100,
        completionTokens = 101,
        totalTokens = 201
    )
)

/**
 * Test fixture for [CreateEditResponse]
 */
class CreateEditResponseTest : DataClassSerializationCommonTest<CreateEditResponse>(
    serializer(),
    createEditResponse,
    expectedSerializedString = """{"object":"Test object","created":100000000,"choices":[{"text":"Test text 1","index":0},{"text":"Test text 2","index":1}],"usage":{"prompt_tokens":100,"completion_tokens":101,"total_tokens":201}}""",
    invalidSerializedString = """{"object":"Test object","created":100000000,"choices":[{"text":"Test text 1","index":0},{"text":"Test text 2","index":1}],"usage":{"promptTokens":100,"completionTokens":101,"totalTokens":201}}"""
)
