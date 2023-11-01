package org.arvarik.openai.core.api

import kotlinx.serialization.serializer
import org.arvarik.openai.core.api.common.DataClassSerializationCommonTest

private val eventLog =
    EventLog(
        objectType = "fine-tune-event",
        createdAt = 1614807352,
        level = "info",
        message = "Job enqueued. Waiting for jobs ahead to complete. Queue number: 0.",
    )

/**
 * Test fixture for [EventLog].
 */
class EventLogTest : DataClassSerializationCommonTest<EventLog>(
    serializer(),
    eventLog,
    expectedSerializedString =
        """
        {
            "object": "fine-tune-event",
            "created_at": 1614807352,
            "level": "info",
            "message": "Job enqueued. Waiting for jobs ahead to complete. Queue number: 0."
        }
        """.trimIndent(),
    invalidSerializedString =
        """
        {
            "created_at": 1614807352,
            "level": "info",
            "message": "Job enqueued. Waiting for jobs ahead to complete. Queue number: 0."
        }
        """.trimIndent(),
)
