package org.arvarik.openai.core.api.moderations

import kotlinx.serialization.serializer
import org.arvarik.openai.core.api.common.DataClassSerializationCommonTest

val createModerationResponse =
    CreateModerationResponse(
        id = "Test id",
        model = "Test model",
        results =
            listOf(
                ModerationResult(
                    Categories(
                        hate = false,
                        hateAndThreatening = false,
                        selfHarm = false,
                        sexual = false,
                        sexualAndMinors = false,
                        violence = true,
                        violenceAndGraphic = true,
                    ),
                    CategoryScores(
                        hate = 0.1,
                        hateAndThreatening = 0.2,
                        selfHarm = 0.3,
                        sexual = 0.4,
                        sexualAndMinors = 0.5,
                        violence = 0.7,
                        violenceAndGraphic = 0.6,
                    ),
                    flagged = true,
                ),
            ),
    )

/**
 * Test fixture for [CreateModerationResponse]
 */
class CreateModerationResponseTest : DataClassSerializationCommonTest<CreateModerationResponse>(
    serializer(),
    createModerationResponse,
    expectedSerializedString =
        """{"id":"Test id","model":"Test model","results":[{"categories":
        |{"hate":false,"hate/threatening":false,"self-harm":false,"sexual":false,"sexual/minors":false,
        |"violence":true,"violence/graphic":true},"category_scores":{"hate":0.1,"hate/threatening":0.2,
        |"self-harm":0.3,"sexual":0.4,"sexual/minors":0.5,"violence":0.7,"violence/graphic":0.6},"flagged":true}]}
        """.trimMargin(),
    invalidSerializedString =
        """{"model":"Test model","results":[{"categories":{"hate":false,
        |"hate/threatening":false,"self-harm":false,"sexual":false,"sexual/minors":false,"violence":true,
        |"violence/graphics":true},"category_scores":{"hate":0.1,"hateAndThreatening":0.2,"selfHarm":0.3,
        |"sexual":0.4,"sexualAndMinors":0.5,"violence":0.7,"violenceAndGraphic":0.6},"flagged":true}]}
        """.trimMargin(),
)
