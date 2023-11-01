package org.arvarik.openai.core.api.finetunes

import kotlinx.serialization.serializer
import org.arvarik.openai.core.api.EventLog
import org.arvarik.openai.core.api.common.DataClassSerializationCommonTest
import org.arvarik.openai.core.api.files.File

private val createFineTuneResponse =
    CreateFineTuneResponse(
        id = "ft-AF1WoRqd3aJAHsqc9NY7iL8F",
        objectType = "fine-tune",
        model = "curie",
        createdAt = 1614807352,
        events =
            listOf(
                EventLog(
                    objectType = "fine-tune-event",
                    createdAt = 1614807352,
                    level = "info",
                    message = "Job enqueued. Waiting for jobs ahead to complete. Queue number: 0.",
                ),
            ),
        fineTunedModel = "test-model",
        hyperParams =
            FineTuneHyperParams(
                batchSize = 4,
                learningRateMultiplier = 0.1,
                numberOfEpochs = 4,
                promptLossWeight = 0.1,
            ),
        organizationId = "org-test",
        resultFiles = listOf(),
        status = "pending",
        validationFiles = listOf(),
        trainingFiles =
            listOf(
                File(
                    id = "file-XGinujblHPwGLSztz8cPS8XY",
                    objectType = "file",
                    bytes = 1547276,
                    createdAt = 1610062281,
                    fileName = "my-data-train.jsonl",
                    purpose = "fine-tune-train",
                ),
            ),
        updatedAt = 1614807352,
    )

/**
 * Test fixture for [CreateFineTuneResponse].
 */
class CreateFineTuneResponseTest : DataClassSerializationCommonTest<CreateFineTuneResponse>(
    serializer(),
    createFineTuneResponse,
    expectedSerializedString =
        """
        {
            "id": "ft-AF1WoRqd3aJAHsqc9NY7iL8F",
            "object": "fine-tune",
            "model": "curie",
            "created_at": 1614807352,
            "events": [
                {
                    "object": "fine-tune-event",
                    "created_at": 1614807352,
                    "level": "info",
                    "message": "Job enqueued. Waiting for jobs ahead to complete. Queue number: 0."
                }
            ],
            "fine_tuned_model": "test-model",
            "hyperparams": {
                "batch_size": 4,
                "learning_rate_multiplier": 0.1,
                "n_epochs": 4,
                "prompt_loss_weight": 0.1
            },
            "organization_id": "org-test",
            "result_files": [],
            "status": "pending",
            "validation_files": [], 
            "training_files": [
                {
                    "id": "file-XGinujblHPwGLSztz8cPS8XY",
                    "object": "file",
                    "bytes": 1547276,
                    "created_at": 1610062281,
                    "filename": "my-data-train.jsonl",
                    "purpose": "fine-tune-train"
                }
            ],
            "updated_at": 1614807352
        }
        """.trimIndent(),
    invalidSerializedString =
        """
        {
            "object": "fine-tune",
            "model": "curie",
            "created_at": 1614807352,
            "events": [],
            "fine_tuned_model": null,
            "hyperparams": {
                "batch_size": 4,
                "learning_rate_multiplier": 0.1,
                "n_epochs": 4,
                "prompt_loss_weight": 0.1
            },
            "organization_id": "org-...",
            "result_files": [],
            "status": "pending",
            "validation_files": [],
            "training_files": [
                {
                    "id": "file-XGinujblHPwGLSztz8cPS8XY",
                    "object": "file",
                    "bytes": 1547276,
                    "created_at": 1610062281,
                    "filename": "my-data-train.jsonl",
                    "purpose": "fine-tune-train"
                }
            ],
            "updated_at": 1614807352
        }
        """.trimIndent(),
)
