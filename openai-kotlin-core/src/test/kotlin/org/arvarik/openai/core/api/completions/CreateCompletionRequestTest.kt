package org.arvarik.openai.core.api.completions

import kotlinx.serialization.serializer
import org.arvarik.openai.core.api.GPT3Model
import org.arvarik.openai.core.api.common.DataClassSerializationCommonTest

val createCompletionRequest = CreateCompletionRequest(
    model = GPT3Model.DAVINCI.modelName,
    prompt = "Test prompt",
    suffix = "suffix",
    maxTokens = 20,
    temperature = 0.5,
    topP = 0.1,
    presencePenalty = 1.0,
    bestOf = 15,
    logitBias = mapOf("1" to 25, "2" to 26)
)

/**
 * Test fixture for [CreateCompletionRequest]
 */
class CreateCompletionRequestTest : DataClassSerializationCommonTest<CreateCompletionRequest>(
    serializer(),
    createCompletionRequest,
    expectedSerializedString = """{"model":"text-davinci-003","prompt":"Test prompt","suffix":"suffix","max_tokens":20,"temperature":0.5,"top_p":0.1,"presence_penalty":1.0,"best_of":15,"logit_bias":{"1":25,"2":26}}""",
    invalidSerializedString = """{"model":"text-davinci-003","prompt":"Test prompt","suffix":"suffix","maxTokens":20,"temperature":0.5,"top_p":0.1,"presencePenalty":1.0,"bestOf":15,"logitBias":{"1":25,"2":26}}"""
)
