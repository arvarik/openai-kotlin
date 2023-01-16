package org.arvarik.openai.client.internal.io

import okio.FileSystem

internal actual val LocalFileSystem: FileSystem = FileSystem.SYSTEM
