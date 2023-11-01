package org.arvarik.openai.core.api.finetunes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.arvarik.openai.core.api.OpenAIRequest

/**
 * The request object for create fine-tune API.
 *
 * @see [Fine-Tune Create API](https://beta.openai.com/docs/api-reference/fine-tunes/create)
 * @see [See the fine-tuning guide for more details](https://beta.openai.com/docs/guides/fine-tuning/creating-training-data).
 */
@Serializable
data class CreateFineTuneRequest(
    /**
     * The ID of an uploaded file that contains training data. Your dataset must be formatted as a JSONL file, where each
     * training example is a JSON object with the keys "prompt" and "completion". Additionally, you must upload your file
     * with the purpose fine-tune.
     */
    @SerialName("training_file")
    val trainingFileId: String,
    /**
     * The ID of an uploaded file that contains validation data.
     * If you provide this file, the data is used to generate validation metrics periodically during fine-tuning.
     * These metrics can be viewed in the fine-tuning results file. Your train and validation data should be mutually
     * exclusive. Your dataset must be formatted as a JSONL file, where each validation example is a JSON object with
     * the keys "prompt" and "completion". Additionally, you must upload your file with the purpose fine-tune.
     */
    @SerialName("validation_file")
    val validationFileId: String? = null,
    /**
     * The name of the base model to fine-tune. Defaults to "curie". Example "ada"
     *
     * @see [See latest models](https://beta.openai.com/docs/models/gpt-3)
     */
    val model: String? = null,
    /**
     * The number of epochs to train the model for. An epoch refers to one full cycle through the training dataset.
     * Defaults to 4
     */
    @SerialName("n_epochs")
    val numberOfEpochs: Int? = null,
    /**
     * The batch size to use for training. The batch size is the number of training examples used to train a single
     * forward and backward pass. By default, the batch size will be dynamically configured to be ~0.2% of the number
     * of examples in the training set, capped at 256 - in general, we've found that larger batch sizes tend to work
     * better for larger datasets.
     */
    @SerialName("batch_size")
    val batchSize: Int? = null,
    /**
     * The learning rate multiplier to use for training. The fine-tuning learning rate is the original learning rate
     * used for pretraining multiplied by this value.
     *
     * By default, the learning rate multiplier is the 0.05, 0.1, or 0.2 depending on final batchSize (larger learning
     * rates tend to perform better with larger batch sizes).
     *
     * We recommend experimenting with values in the range 0.02 to 0.2 to see what produces the best results.
     */
    @SerialName("learning_rate_multiplier")
    val learningRateMultiplier: Double? = null,
    /**
     * The weight to use for loss on the prompt tokens. This controls how much the model tries to learn to generate
     * the prompt (as compared to the completion which always has a weight of 1.0), and can add a stabilizing effect to
     * training when completions are short. If prompts are extremely long (relative to completions), it may make sense
     * to reduce this weight so as to avoid over-prioritizing learning the prompt.
     *
     * Defaults to 0.01
     */
    @SerialName("prompt_loss_weight")
    val promptLossWeight: Double? = null,
    /**
     * If set, it calculates classification-specific metrics such as accuracy and F-1 score using the validation set at
     * the end of every epoch. These metrics can be viewed in the results file. In order to compute classification metrics,
     * you must provide a validation_file. Additionally, you must specify classification_n_classes for multiclass
     * classification or classification_positive_class for binary classification.
     *
     * Defaults to false
     */
    @SerialName("compute_classification_metrics")
    val computeClassificationMetrics: Boolean? = null,
    /**
     * The number of classes in a classification task. This parameter is required for multiclass classification.
     */
    @SerialName("classification_n_classes")
    val classificationClassesNumber: Int? = null,
    /**
     * The positive class in binary classification. This parameter is needed to generate precision, recall, and F1 metrics
     * when doing binary classification.
     */
    @SerialName("classification_positive_class")
    val classificationPositiveClass: String? = null,
    /**
     * If this is provided, we calculate F-beta scores at the specified beta values. The F-beta score is a generalization
     * of F-1 score. This is only used for binary classification. With a beta of 1 (i.e. the F-1 score), precision and
     * recall are given the same weight. A larger beta score puts more weight on recall and less on precision.
     * A smaller beta score puts more weight on precision and less on recall.
     */
    @SerialName("classification_betas")
    val classificationBetas: List<Double>? = null,
    /**
     * A string of up to 40 characters that will be added to your fine-tuned model name.
     * For example, a suffix of "custom-model-name" would produce a model name like
     * ada:ft-your-org:custom-model-name-2022-02-15-04-21-04
     */
    val suffix: String? = null,
) : OpenAIRequest
