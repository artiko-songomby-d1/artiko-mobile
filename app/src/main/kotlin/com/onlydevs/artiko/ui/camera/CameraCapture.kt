package com.onlydevs.artiko.ui.camera

import android.content.Context
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.LifecycleCameraController
import androidx.core.content.ContextCompat
import java.io.File
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlinx.coroutines.suspendCancellableCoroutine

/** Takes a photo and returns the JPEG file, which keeps its rotation in the EXIF data. */
suspend fun LifecycleCameraController.takePhoto(context: Context): File = suspendCancellableCoroutine { continuation ->
    val file = File.createTempFile("photo", ".jpg", context.cacheDir)
    takePicture(
        ImageCapture.OutputFileOptions.Builder(file).build(),
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(output: ImageCapture.OutputFileResults) = continuation.resume(file)

            override fun onError(exception: ImageCaptureException) = continuation.resumeWithException(exception)
        },
    )
}
