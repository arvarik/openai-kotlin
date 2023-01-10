package org.arvarik.openai.core.api.embeddings

import kotlinx.serialization.serializer
import org.arvarik.openai.core.api.GPT3Model
import org.arvarik.openai.core.api.Usage
import org.arvarik.openai.core.api.common.DataClassSerializationCommonTest

val createEmbeddingsResponse = CreateEmbeddingsResponse(
    `object` = "Test object",
    model = GPT3Model.ADA.modelName,
    data = listOf(
        Embedding(
            `object` = "Test embedding 1",
            embedding = listOf(0.0, 1.0, 2.0),
            index = 0
        ),
        Embedding(
            `object` = "Test embedding 2",
            embedding = listOf(3.0, 4.0, 5.0),
            index = 0
        )
    ),
    usage = Usage(
        promptTokens = 100,
        totalTokens = 201
    )
)

/**
 * Test fixture for [CreateEmbeddingsResponse]
 */
class CreateEmbeddingsResponseTest : DataClassSerializationCommonTest<CreateEmbeddingsResponse>(
    serializer(),
    createEmbeddingsResponse,
    expectedSerializedString = """{"object":"Test object","data":[{"object":"Test embedding 1","embedding":[0.0,1.0,2.0],"index":0},{"object":"Test embedding 2","embedding":[3.0,4.0,5.0],"index":0}],"model":"text-ada-001","usage":{"prompt_tokens":100,"total_tokens":201}}""",
    invalidSerializedString = """{"object":"Test object","data":[{"object":"Test embedding 1","embedding":[0.0,1.0,2.0],"index":0},{"object":"Test embedding 2","embedding":[3.0,4.0,5.0],"index":0}],"model":"text-ada-001","usage":{"promptTokens":100,"totalTokens":201}}"""
)
