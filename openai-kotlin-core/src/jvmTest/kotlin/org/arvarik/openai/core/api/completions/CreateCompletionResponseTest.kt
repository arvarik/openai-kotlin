package org.arvarik.openai.core.api.completions

import kotlinx.serialization.serializer
import org.arvarik.openai.core.api.GPT3Model
import org.arvarik.openai.core.api.Usage
import org.arvarik.openai.core.api.common.DataClassSerializationCommonTest

val createCompletionResponse =
    CreateCompletionResponse(
        id = "Test id",
        `object` = "Test object",
        created = 10000000L,
        model = GPT3Model.DAVINCI.modelName,
        choices =
            listOf(
                Completion(
                    text = "Test completion text",
                    index = 0,
                    logprobs =
                        Logprobs(
                            tokens = listOf("Test", "token"),
                            tokenLogprobs = listOf(.2, .8),
                            topLogprobs = listOf(mapOf("token" to 0.8)),
                            textOffset = listOf(0),
                        ),
                    finishReason = "Test finish reason",
                ),
            ),
        usage =
            Usage(
                promptTokens = 100,
                completionTokens = 101,
                totalTokens = 201,
            ),
    )

/**
 * Test fixture for [CreateCompletionResponse]
 */
class CreateCompletionResponseTest : DataClassSerializationCommonTest<CreateCompletionResponse>(
    serializer(),
    createCompletionResponse,
    expectedSerializedString =
        """{"id":"Test id","object":"Test object","created":10000000,"model":"text-davinci-003",
        |"choices":[{"text":"Test completion text","index":0,"logprobs":{"tokens":["Test","token"],"token_logprobs":[0.2,0.8],
        |"top_logprobs":[{"token":0.8}],"text_offset":[0]},"finish_reason":"Test finish reason"}],
        |"usage":{"prompt_tokens":100,"completion_tokens":101,"total_tokens":201}}
        """.trimMargin(),
    invalidSerializedString =
        """{"id":"Test id","object":"Test object","created":10000000,"model":"text-davinci-003",
        |"choices":[{"text":"Test completion text","index":0,"logprobs":{"tokens":["Test","token"],"tokenLogprobs":[0.2,0.8],
        |"topLogprobs":[{"token":0.8}],"textOffset":[0]},"finishReason":"Test finish reason"}],
        |"usage":{"promptTokens":100,"completionTokens":101,"totalTokens":201}}
        """.trimMargin(),
)
