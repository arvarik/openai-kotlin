package org.arvarik.openai.core.api.files

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represent an Open AI file ID information.
 */
@Serializable
data class File(
    /**
     * The ID of te file. Example, file-XjGxS3KTG0uNmNOK362iJua3
     */
    val id: String,
    /**
     * The object type. Example, file
     */
    @SerialName("object")
    val objectType: String,
    /**
     * The file size in Bytes.
     */
    val bytes: Long,
    /**
     * File created at epoch date.
     */
    @SerialName("created_at")
    val createdAt: Long,
    /**
     * The file name.
     */
    @SerialName("filename")
    val fileName: String,
    /**
     * The purpose of the file.
     */
    val purpose: String,
)
