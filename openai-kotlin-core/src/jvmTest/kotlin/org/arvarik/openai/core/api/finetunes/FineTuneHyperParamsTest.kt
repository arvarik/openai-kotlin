package org.arvarik.openai.core.api.finetunes

import kotlinx.serialization.serializer
import org.arvarik.openai.core.api.common.DataClassSerializationCommonTest

private val fineTuneHyperParams = FineTuneHyperParams(
    batchSize = 4,
    learningRateMultiplier = 0.1,
    numberOfEpochs = 4,
    promptLossWeight = 0.1
)

/**
 * Test fixture for [FineTuneHyperParams].
 */
class FineTuneHyperParamsTest : DataClassSerializationCommonTest<FineTuneHyperParams>(
    serializer(),
    fineTuneHyperParams,
    expectedSerializedString = """
        {
            "batch_size": 4,
            "learning_rate_multiplier": 0.1,
            "n_epochs": 4,
            "prompt_loss_weight": 0.1
        }
    """.trimIndent(),

    invalidSerializedString = """
        {
            "batch_size": "string",
            "learning_rate_multiplier": 0.1,
            "n_epochs": 4,
            "prompt_loss_weight": 0.1
        }
    """.trimIndent()
)
