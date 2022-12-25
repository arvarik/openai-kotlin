package org.arvarik.openai.core.api.retrieve

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.arvarik.openai.core.api.OpenAIResponse
//import org.arvarik.openai.core.api.Usage
//don't think I need this'

data class CreateRetrieveResponse{
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
    val `permission`: List<String>,
}



