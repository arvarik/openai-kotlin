package org.arvarik.openai.core.api.images

import kotlinx.serialization.serializer
import org.arvarik.openai.core.api.common.DataClassSerializationCommonTest

val createImageRequest =
    CreateImageRequest(
        prompt = "Test prompt",
        n = 2,
        size = "256x256",
        responseFormat = "url",
    )

class CreateImageRequestTest : DataClassSerializationCommonTest<CreateImageRequest>(
    serializer(),
    createImageRequest,
    expectedSerializedString = """{"prompt":"Test prompt","n":2,"size":"256x256","response_format":"url"}""",
    invalidSerializedString = """{"prompt":"Test prompt","n":2,"size":"256x256","responseFormat":"url"}""",
)
