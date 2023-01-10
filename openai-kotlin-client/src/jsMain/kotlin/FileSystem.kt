package org.arvarik.openai.client.internal.io

import okio.FileSystem
import okio.NodeJsFileSystem

internal actual val LocalFileSystem: FileSystem = NodeJsFileSystem
