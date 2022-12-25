package org.arvarik.openai.core.api.models.ListModels

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
     * The data associated with each model
     */
    val data: List<ModelData>,

    /**
     * The object type of the response
     */
    val `object`: String
) : OpenAIResponse

/**
 * Data on a single model
 */
@Serializable
data class ModelData(
    /**
     * The id associated with the model
     */
    val id: String,

    /**
     * The object type of the model
     */
    val `object`: String,

    /**
     * The organization which owns the model
     */
    val owned_by: String,

    /**
     * Data structure describing the permissions associated with the model
     */
    val permission: List<PermissionData>
)

/**
 * Data on permissions for a single model
 */
@Serializable
data class PermissionData(
    /**
     * The id associated with the model
     */
    val id: String
)