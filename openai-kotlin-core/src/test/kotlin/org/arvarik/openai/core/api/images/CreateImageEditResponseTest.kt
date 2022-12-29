package org.arvarik.openai.core.api.images

import kotlinx.serialization.serializer
import org.arvarik.openai.core.api.common.DataClassSerializationCommonTest

val createImageEditResponse = CreateImageEditResponse(
    created = 100000000L,
    data = listOf(
        Image(url = "Test url 1"),
        Image(url = "Test url 2")
    )
)

class CreateImageEditResponseTest : DataClassSerializationCommonTest<CreateImageEditResponse>(
    serializer(),
    createImageEditResponse,
    expectedSerializedString = """{"created":100000000,"data":[{"url":"Test url 1"},{"url":"Test url 2"}]}""",
    invalidSerializedString = """{"created":100000000,"datum":[{"url":"Test url 1"},{"url":"Test url 2"}]}"""
)
