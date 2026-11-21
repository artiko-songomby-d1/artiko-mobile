package com.onlydevs.artiko.ui.camera

import android.util.Log
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.onlydevs.artiko.R

private const val TAG = "ArtikoCamera"
private val ERROR_PADDING = 24.dp

/** The live camera image, bound to the lifecycle of the screen. */
@Composable
fun CameraPreview(controller: LifecycleCameraController, modifier: Modifier = Modifier) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    var failure by remember(controller) { mutableStateOf<String?>(null) }
    // A camera that cannot start used to leave a silent black box: say why instead.
    DisposableEffect(controller) {
        val initialization = controller.initializationFuture
        initialization.addListener(
            {
                runCatching { initialization.get() }.onFailure {
                    Log.e(TAG, "The camera could not start", it)
                    failure = it.cause?.message ?: it.message ?: it.javaClass.simpleName
                }
            },
            ContextCompat.getMainExecutor(context),
        )
        onDispose {}
    }
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { viewContext ->
                PreviewView(viewContext).apply {
                    // Texture view: it can be clipped to rounded corners and drawn under the viewfinder frame.
                    implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                    scaleType = PreviewView.ScaleType.FILL_CENTER
                    this.controller = controller
                    controller.bindToLifecycle(lifecycleOwner)
                }
            },
        )
        failure?.let {
            Text(
                text = stringResource(R.string.camera_unavailable, it),
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(ERROR_PADDING),
            )
        }
    }
}

/** A camera controller that runs the given CameraX use cases, such as `CameraController.IMAGE_CAPTURE`. */
@Composable
fun rememberCameraController(useCases: Int): LifecycleCameraController {
    val context = LocalContext.current
    return remember(useCases) { LifecycleCameraController(context).apply { setEnabledUseCases(useCases) } }
}
