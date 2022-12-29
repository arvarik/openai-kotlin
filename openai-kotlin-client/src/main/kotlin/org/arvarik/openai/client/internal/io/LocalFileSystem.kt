package org.arvarik.openai.client.internal.io

import okio.FileSystem
import okio.Path.Companion.toPath
import org.arvarik.openai.client.OpenAIClientConfig

class LocalFileSystem(private val config: OpenAIClientConfig, private val fileSystem: FileSystem) {

    class LocalFileInBytes(val filepath: String, val bytes: ByteArray)

    fun readImageFileInBytes(imageFilename: String): LocalFileInBytes {
        val filepath = when (config.imagesFilepath) {
            null -> imageFilename
            else -> config.imagesFilepath + imageFilename
        }
        return LocalFileInBytes(filepath, fileSystem.read(filepath.toPath()) { readByteArray() })
    }
}
