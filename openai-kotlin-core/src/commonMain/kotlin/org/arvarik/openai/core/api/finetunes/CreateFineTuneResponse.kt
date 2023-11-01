package org.arvarik.openai.core.api.finetunes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.arvarik.openai.core.api.EventLog
import org.arvarik.openai.core.api.OpenAIResponse
import org.arvarik.openai.core.api.files.File

/**
 * The response object for create fine-tune API.
 *
 * @see [Fine-Tune Create API](https://beta.openai.com/docs/api-reference/fine-tunes/create)
 * @see [See the fine-tuning guide for more details](https://beta.openai.com/docs/guides/fine-tuning/creating-training-data).
 */
@Serializable
data class CreateFineTuneResponse(
    /**
     * The ID of the create fine tune request. Example, ft-AF1WoRqd3aJAHsqc9NY7iL8F
     */
    val id: String,
    /**
     * The response object type. Example, fine-tune
     */
    @SerialName("object")
    val objectType: String,
    /**
     * The base model to fine tune name. Example, curie
     */
    val model: String,
    /**
     * Request create epoch date.
     */
    @SerialName("created_at")
    val createdAt: Long,
    /**
     * List of event logs of the fine-tuned model.
     */
    val events: List<EventLog>,
    /**
     * The name of the fined tuned model.
     */
    @SerialName("fine_tuned_model")
    val fineTunedModel: String? = null,
    /**
     * The parameters used in the fine-tunes request.
     */
    @SerialName("hyperparams")
    val hyperParams: FineTuneHyperParams,
    /**
     * The organization id.
     */
    @SerialName("organization_id")
    val organizationId: String? = null,
    /**
     * List of the result file IDs.
     */
    @SerialName("result_files")
    val resultFiles: List<String>,
    /**
     * The status of the request, Example, Pending.
     */
    val status: String,
    /**
     * The files ID of the validation files.
     */
    @SerialName("validation_files")
    val validationFiles: List<String>,
    /**
     * The uploaded training files information.
     */
    @SerialName("training_files")
    val trainingFiles: List<File>,
    /**
     * Request update at epoch date.
     */
    @SerialName("updated_at")
    val updatedAt: Long,
) : OpenAIResponse
