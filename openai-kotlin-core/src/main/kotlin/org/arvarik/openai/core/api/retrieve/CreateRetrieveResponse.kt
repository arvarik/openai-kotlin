package org.arvarik.openai.core.api.retrieve

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.arvarik.openai.core.api.OpenAIResponse
//import org.arvarik.openai.core.api.Usage
//don't think I need this

data class CreateRetrieveResponse(
    /**
     * The id associated with the completion
     */
    val id: String,

    /**
     * The model of the object
     */
    val `object`: String,

    /**
     * The owner of the object
     */
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
    val parent: String?
    )

data class Permission(
    /**
     * id associated with the model
     */
    val id: String,

    /**
     * Object type of permission
     */
    val `object`: String,

    /**
     * Creation time in epoch seconds?
     */
    val created: Long,

    /**
     * Whether engine is allowed to be created
     */
    @SerialName("allow_create_engine")
    val allowCreateEngine: Boolean,

    /**
     * Sampling allowed/not allowed
     */
    @SerialName("allow_sampling")
    val allowSampling: Boolean,

    /**
     * Whether logprobs is allowed
     */
    @SerialName("allow_logprobs")
    val allowLogprobs: Boolean,

    /**
     * Whether search indices is allowed
     */
    @SerialName("allow_search_indices")
    val allowSearchIndices: Boolean,

    /**
     * Whether view is allowed
     */
    @SerialName("allow_view")
    val allowView: Boolean,

    /**
     * Whether fine tuning is allowed
     */
    @SerialName("is_blocking")
    val allowFineTuning: Boolean,

    /**
     * Organization of the model
     */
    val organization: String,

    /**
     * Group to the model
     */
    val group: String?,

    /**
     * Whether model is blocking
     */
    @SerialName("is_blocking")
    val isBlocking: Boolean
)
