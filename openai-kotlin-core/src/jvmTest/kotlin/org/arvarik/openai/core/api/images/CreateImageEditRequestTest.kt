package org.arvarik.openai.core.api.images

import kotlinx.serialization.serializer
import org.arvarik.openai.core.api.common.DataClassSerializationCommonTest

val createImageEditRequest = CreateImageEditRequest(
    image = "Test image",
    mask = "Test mask",
    prompt = "Test prompt",
    n = 3,
    responseFormat = "url"
)

class CreateImageEditRequestTest : DataClassSerializationCommonTest<CreateImageEditRequest>(
    serializer(),
    createImageEditRequest,
    expectedSerializedString = """{"image":"Test image","mask":"Test mask","prompt":"Test prompt","n":3,"response_format":"url"}""",
    invalidSerializedString = """{"image":"Test image","mask":"Test mask","prompt":"Test prompt","n":3,"responseFormat":"url"}"""
)
