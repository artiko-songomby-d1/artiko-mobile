package com.onlydevs.artiko.ui.camera

import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner

/** The live camera image, bound to the lifecycle of the screen. */
@Composable
fun CameraPreview(controller: LifecycleCameraController, modifier: Modifier = Modifier) {
    val lifecycleOwner = LocalLifecycleOwner.current
    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->
            PreviewView(context).apply {
                // Texture view: it can be clipped to rounded corners and drawn under the viewfinder frame.
                implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                this.controller = controller
                controller.bindToLifecycle(lifecycleOwner)
            }
        },
    )
}

/** A camera controller that runs the given CameraX use cases, such as `CameraController.IMAGE_CAPTURE`. */
@Composable
fun rememberCameraController(useCases: Int): LifecycleCameraController {
    val context = LocalContext.current
    return remember(useCases) { LifecycleCameraController(context).apply { setEnabledUseCases(useCases) } }
}
