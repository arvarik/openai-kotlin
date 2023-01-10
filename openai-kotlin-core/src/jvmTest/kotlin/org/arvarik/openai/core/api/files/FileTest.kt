package org.arvarik.openai.core.api.files

import kotlinx.serialization.serializer
import org.arvarik.openai.core.api.common.DataClassSerializationCommonTest

private val fileIdInfo = File(
    id = "file-XGinujblHPwGLSztz8cPS8XY",
    objectType = "file",
    bytes = 1547276,
    createdAt = 1610062281,
    fileName = "my-data-train.jsonl",
    purpose = "fine-tune-train"
)

/**
 * Test fixture for [File].
 */
class FileTest : DataClassSerializationCommonTest<File>(
    serializer(),
    fileIdInfo,
    expectedSerializedString = """
            {
                "id": "file-XGinujblHPwGLSztz8cPS8XY",
                "object": "file",
                "bytes": 1547276,
                "created_at": 1610062281,
                "filename": "my-data-train.jsonl",
                "purpose": "fine-tune-train"
            } 
    """.trimIndent(),

    invalidSerializedString = """
            {   
                "object": "file",
                "bytes": 1547276,
                "created_at": 1610062281,
                "filename": "my-data-train.jsonl",
                "purpose": "fine-tune-train"
            }
    """.trimIndent()
)
