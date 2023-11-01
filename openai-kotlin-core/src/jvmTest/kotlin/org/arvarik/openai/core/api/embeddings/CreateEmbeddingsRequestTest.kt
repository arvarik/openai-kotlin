package org.arvarik.openai.core.api.embeddings

import kotlinx.serialization.serializer
import org.arvarik.openai.core.api.GPT3Model
import org.arvarik.openai.core.api.common.DataClassSerializationCommonTest

val createEmbeddingsRequest =
    CreateEmbeddingsRequest(
        model = GPT3Model.ADA.modelName,
        input = listOf("Test input 1", "Test input 2"),
    )

/**
 * Test fixture for [CreateEmbeddingsRequest]
 */
class CreateEmbeddingsRequestTest : DataClassSerializationCommonTest<CreateEmbeddingsRequest>(
    serializer(),
    createEmbeddingsRequest,
    expectedSerializedString = """{"model":"text-ada-001","input":["Test input 1","Test input 2"]}""",
    invalidSerializedString = """{"models":"text-ada-001","input":["Test input 1","Test input 2"]}""",
)
