package org.arvarik.openai.core.api.finetunes

import kotlinx.serialization.serializer
import org.arvarik.openai.core.api.common.DataClassSerializationCommonTest

private val createFineTuneRequest = CreateFineTuneRequest(
    trainingFileId = "trainingUploadedFileId",
    validationFileId = "validationUploadedFileId",
    model = "ada",
    numberOfEpochs = 4,
    batchSize = 256,
    learningRateMultiplier = 0.2,
    promptLossWeight = 0.01,
    computeClassificationMetrics = false,
    classificationClassesNumber = 10,
    classificationPositiveClass = "classificationPositiveClass",
    classificationBetas = listOf(1.0, 2.0, 3.0),
    suffix = "test"
)

/**
 * Test fixture for [CreateFineTuneRequest].
 */
class CreateFineTuneRequestTest : DataClassSerializationCommonTest<CreateFineTuneRequest>(
    serializer(),
    createFineTuneRequest,
    expectedSerializedString = """
        {
           "training_file": "trainingUploadedFileId",
           "validation_file": "validationUploadedFileId",
           "model": "ada",
           "n_epochs": 4,
           "batch_size": 256,
           "learning_rate_multiplier": 0.2,
           "prompt_loss_weight": 0.01,
           "compute_classification_metrics": false,
           "classification_n_classes": 10,
           "classification_positive_class": "classificationPositiveClass",
           "classification_betas": [1.0, 2.0, 3.0],
           "suffix": "test"  
        }
    """.trimIndent(),
    invalidSerializedString = """
        {
           "validation_file": "validationUploadedFileId",
           "model": "ada",
           "n_epochs": 4,
           "batch_size": 256,
           "learning_rate_multiplier": 0.2,
           "prompt_loss_weight": 0.01,
           "compute_classification_metrics": false,
           "classification_n_classes": 10,
           "classification_positive_class": "classificationPositiveClass",
           "classification_betas": [1.0, 2.0, 3.0],
        }
    """.trimIndent()
)
