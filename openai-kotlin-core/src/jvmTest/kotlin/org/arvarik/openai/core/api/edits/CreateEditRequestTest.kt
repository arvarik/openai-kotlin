package org.arvarik.openai.core.api.edits

import kotlinx.serialization.serializer
import org.arvarik.openai.core.api.GPT3Model
import org.arvarik.openai.core.api.common.DataClassSerializationCommonTest

val createEditRequest =
    CreateEditRequest(
        model = GPT3Model.DAVINCI.modelName,
        input = "Test input",
        instruction = "Test instruction",
        n = 2,
        temperature = 0.6,
        topP = 0.4,
    )

/**
 * Test fixture for [CreateEditRequest]
 */
class CreateEditRequestTest : DataClassSerializationCommonTest<CreateEditRequest>(
    serializer(),
    createEditRequest,
    expectedSerializedString =
        """{"model":"text-davinci-003","input":"Test input","instruction":"Test instruction","n":2,
        |"temperature":0.6,"top_p":0.4}
        """.trimMargin(),
    invalidSerializedString =
        """{"model":"text-davinci-003","input":"Test input","instruction":"Test instruction","n":2,
        |"temperature":0.6,"topP":0.4}
        """.trimMargin(),
)
