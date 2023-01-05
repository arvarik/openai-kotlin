package org.arvarik.openai.core.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * An OpenAI event log returned in API responses.
 */
@Serializable
data class EventLog(
    /**
     * The object type. Example, "fine-tune-event"
     */
    @SerialName("object")
    val objectType: String,

    /**
     * Epoch represent when the event has been created.
     */
    @SerialName("created_at")
    val createdAt: Long,

    /**
     * Event log level. Example, "info"
     */
    val level: String,

    /**
     * Event log message.
     */
    val message: String
)
