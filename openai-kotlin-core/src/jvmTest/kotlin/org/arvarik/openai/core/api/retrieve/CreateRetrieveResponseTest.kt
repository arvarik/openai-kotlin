package org.arvarik.openai.core.api.retrieve

import kotlinx.serialization.serializer
import org.arvarik.openai.core.api.common.DataClassSerializationCommonTest

val createRetrieveModelResponse = CreateRetrieveModelResponse(
    id = "Test id",
    `object` = "Test model",
    created = 1671910080L,
    ownedBy = "String",
    permission = listOf(
        Permission(
            id = "Test id",
            `object` = "Test model",
            created = 1671910080L,
            allowCreateEngine = false,
            allowSampling = true,
            allowLogprobs = true,
            allowSearchIndices = false,
            allowView = true,
            allowFineTuning = false,
            organization = "*",
            group = null,
            isBlocking = false
        )
    ),
    root = "Test root",
    parent = null
)

/**
 * Test fixture for [CreateRetrieveModelResponse]
 */
class CreateRetrieveResponseTest : DataClassSerializationCommonTest<CreateRetrieveModelResponse>(
    serializer(),
    createRetrieveModelResponse,
    expectedSerializedString = """{"id":"Test id","object":"Test model","created":1671910080,"owned_by":"String","permission":[{"id":"Test id","object":"Test model","created":1671910080,"allow_create_engine":false,"allow_sampling":true,"allow_logprobs":true,"allow_search_indices":false,"allow_view":true,"allow_fine_tuning":false,"organization":"*","is_blocking":false}],"root":"Test root"}""",
    invalidSerializedString = """{"id":"Test id","object":"Test model","created":1671910080,"ownedBy":"String","permission":[{"id":"Test id","object":"Test model","created":1671910080,"allowCreateEngine":false,"allowSampling":true,"allowLogprobs":true,"allowSearchIndices":false,"allowView":true,"allowFineTuning":false,"organization":"*","isBlocking":false}],"root":"Test root"}"""
)