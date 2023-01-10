package org.arvarik.openai.core.api.models.listmodels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.arvarik.openai.core.api.OpenAIResponse

/**
 * Response object for the /models endpoint
 *
 * Lists the currently available models, and provides basic information about each one such as the owner and availability
 *
 * Documentation: https://beta.openai.com/docs/api-reference/models/list
 */
@Serializable
data class ListModelsResponse(
    /**
     * The object type of the response
     */
    @SerialName("object")
    val objectType: String,

    /**
     * The data associated with each model
     */
    val data: List<Model>
) : OpenAIResponse

/**
 * Data on a single model
 */
@Serializable
data class Model(
    /**
     * The id associated with the model
     */
    val id: String,

    /**
     * The object type of the model
     */
    @SerialName("object")
    val objectType: String,

    /**
     * The creation time in epoch seconds
     */
    val created: Long,

    /**
     * The organization which owns the model
     */
    @SerialName("owned_by")
    val ownedBy: String,

    /**
     * Permissions associated with the model
     */
    val permission: List<Permission>,

    /**
     * Root model
     */
    val root: String,

    /**
     * Parent model
     */
    val parent: String? = null
)

/**
 * Permissions associated with a single model
 */
@Serializable
data class Permission(
    /**
     * The id associated with the model
     */
    val id: String,

    /**
     * The object type of the permission
     */
    @SerialName("object")
    val objectType: String,

    /**
     * The creation time in epoch seconds
     */
    val created: Long,

    /**
     * Whether the engine is allowed to be created
     */
    @SerialName("allow_create_engine")
    val allowCreateEngine: Boolean,

    /**
     * Whether sampling is allowed
     */
    @SerialName("allow_sampling")
    val allowSampling: Boolean,

    /**
     * Whether logprobs is allowed
     */
    @SerialName("allow_logprobs")
    val allowLogprobs: Boolean,

    /**
     * Whether search indices are allowed
     */
    @SerialName("allow_search_indices")
    val allowSearchIndices: Boolean,

    /**
     * Whether a view is allowed
     */
    @SerialName("allow_view")
    val allowView: Boolean,

    /**
     * Whether fine tuning is allowed
     */
    @SerialName("allow_fine_tuning")
    val allowFineTuning: Boolean,

    /**
     * The organization associated with the model
     */
    val organization: String,

    /**
     * The group associated with the model
     */
    val group: String? = null,

    /**
     * Whether the model is blocking
     */
    @SerialName("is_blocking")
    val isBlocking: Boolean
)
