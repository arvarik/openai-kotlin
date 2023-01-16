package org.arvarik.openai.core.api.finetunes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * FineTune hyper parameters used for fine tuning.
 */
@Serializable
data class FineTuneHyperParams(
    /**
     * The batch size used to fine tune the model.
     */
    @SerialName("batch_size")
    val batchSize: Int? = null,

    /**
     * The learning rate multiplier used to fine tune the model.
     */
    @SerialName("learning_rate_multiplier")
    val learningRateMultiplier: Double? = null,

    /**
     * The number of epoch cycles used to fine tune the model.
     */
    @SerialName("n_epochs")
    val numberOfEpochs: Int? = null,

    /**
     * The promot loss weight used to fine tune the model.
     */
    @SerialName("prompt_loss_weight")
    val promptLossWeight: Double? = null
)
