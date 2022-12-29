package org.arvarik.openai.client.internal.impl

import io.ktor.client.request.forms.FormBuilder
import io.ktor.client.request.forms.formData
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import org.arvarik.openai.client.Images
import org.arvarik.openai.client.internal.http.OpenAIHTTPClient
import org.arvarik.openai.client.internal.io.LocalFileSystem
import org.arvarik.openai.core.api.images.CreateImageEditRequest
import org.arvarik.openai.core.api.images.CreateImageEditResponse
import org.arvarik.openai.core.api.images.CreateImageRequest
import org.arvarik.openai.core.api.images.CreateImageResponse

internal class ImagesImpl(
    private val httpClient: OpenAIHTTPClient,
    private val localFileSystem: LocalFileSystem
) : Images {

    override suspend fun createImage(request: CreateImageRequest): CreateImageResponse {
        return httpClient.post(request, ImagesGenerationsEndpoint)
    }

    override suspend fun createImageEdit(request: CreateImageEditRequest): CreateImageEditResponse {
        val form = formData {
            appendPngImageInBytes("image", request.image)
            append(key = "prompt", value = request.prompt)

            request.mask?.let { appendPngImageInBytes("key", it) }
            request.n?.let { append(key = "n", value = it) }
            request.size?.let { append(key = "size", value = it) }
            request.responseFormat?.let { append(key = "response_format", value = it) }
            request.user?.let { append(key = "user", value = it) }
        }

        return httpClient.post(form, ImagesEditsEndpoint)
    }

    private fun FormBuilder.appendPngImageInBytes(key: String, filePath: String) {
        val image = localFileSystem.readImageFileInBytes(filePath)
        append(
            key = key,
            image.bytes,
            Headers.build {
                append(HttpHeaders.ContentType, ContentType.Image.PNG.toString())
                append(HttpHeaders.ContentDisposition, "filename=\"${image.filepath}\"")
            }
        )
    }

    companion object {
        private const val ImagesGenerationsEndpoint = "/v1/images/generations"
        private const val ImagesEditsEndpoint = "/v1/images/edits"
    }
}
