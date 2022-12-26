package org.arvarik.openai.core.api.retrieve

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.arvarik.openai.core.api.OpenAIResponse
//import org.arvarik.openai.core.api.Usage
//don't think I need this'

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
    val `ownedBy`: String,

    /**
     * not too sure about what this does
     */
    val `permission`: List<Permission>,

    val root: String,

    val parent: String?,

    )

data class Permission(
    val id: String,

    val object: String,

    val created: Long,

    @SerialName("allow_create_engine")
    val allowCreateEngine: Boolean,

    @SerialName("allow_sampling")
    val allowSampling: Boolean,

    @SerialName("allow_logprobs")
    val allowLogprobs: Boolean,

    @SerialName("allow_search_indices")
    val allowSearchIndices: Boolean,

    @SerialName("allow_view")
    val allowView: Boolean,

    @SerialName("is_blocking")
    val allowFineTuning: Boolean,

    val organization: String,

    val group: String?,

    @SerialName("is_blocking")
    val isBlocking: Boolean

    )




