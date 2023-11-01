package org.arvarik.openai.core.api.images

import kotlinx.serialization.serializer
import org.arvarik.openai.core.api.common.DataClassSerializationCommonTest

val createImageVariationRequest =
    CreateImageVariationRequest(
        image = "Test image",
        n = 3,
        responseFormat = "url",
    )

class CreateImageVariationRequestTest : DataClassSerializationCommonTest<CreateImageVariationRequest>(
    serializer(),
    createImageVariationRequest,
    expectedSerializedString = """{"image":"Test image","n":3,"response_format":"url"}""",
    invalidSerializedString = """{"image":"Test image","n":3,"responseFormat":"url"}""",
)
