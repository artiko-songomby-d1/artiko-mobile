package com.onlydevs.artiko.feature.record

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import androidx.camera.video.FileOutputOptions
import androidx.camera.video.Recording
import androidx.camera.video.VideoRecordEvent
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.video.AudioConfig
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.onlydevs.artiko.ui.camera.CameraPreview
import com.onlydevs.artiko.ui.camera.RequirePermissions
import com.onlydevs.artiko.ui.camera.rememberCameraController
import java.io.File
import kotlinx.coroutines.delay

private const val TICK_MILLIS = 1000L

/** Records a video with sound; [onUpload] receives the file once the visitor chooses to publish it. */
@Composable
fun RecordScreen(onUpload: (String) -> Unit) {
    val context = LocalContext.current
    val controller = rememberCameraController(CameraController.VIDEO_CAPTURE)
    var recording by remember { mutableStateOf<Recording?>(null) }
    var take by remember { mutableStateOf<File?>(null) }
    var seconds by remember { mutableIntStateOf(0) }
    LaunchedEffect(recording) {
        seconds = 0
        while (recording != null) {
            delay(TICK_MILLIS)
            seconds++
        }
    }

    RequirePermissions(
        permissions = listOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO),
        rationale = "Artiko needs the camera and the microphone to record your work",
    ) {
        RecordPage(
            isRecording = recording != null,
            elapsedSeconds = seconds,
            hasRecording = take != null,
            viewfinder = { CameraPreview(controller) },
            onToggle = {
                val current = recording
                if (current != null) {
                    current.stop()
                } else {
                    take = null
                    recording = startRecording(context, controller) { file ->
                        take = file
                        recording = null
                    }
                }
            },
            onUpload = { take?.let { onUpload(it.toUri().toString()) } },
        )
    }
}

@SuppressLint("MissingPermission") // RequirePermissions only shows the screen once the microphone is allowed.
private fun startRecording(context: Context, controller: LifecycleCameraController, onFinished: (File?) -> Unit) =
    File.createTempFile("recording", ".mp4", context.cacheDir).let { file ->
        controller.startRecording(
            FileOutputOptions.Builder(file).build(),
            AudioConfig.create(true),
            ContextCompat.getMainExecutor(context),
        ) { event ->
            if (event is VideoRecordEvent.Finalize) onFinished(if (event.hasError()) null else file)
        }
    }
