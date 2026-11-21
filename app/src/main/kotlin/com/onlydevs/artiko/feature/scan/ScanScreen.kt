package com.onlydevs.artiko.feature.scan

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.camera.view.CameraController
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.onlydevs.artiko.data.readMedia
import com.onlydevs.artiko.ui.artikoViewModel
import com.onlydevs.artiko.ui.camera.CameraPreview
import com.onlydevs.artiko.ui.camera.RequirePermissions
import com.onlydevs.artiko.ui.camera.rememberCameraController
import com.onlydevs.artiko.ui.camera.takePhoto
import kotlinx.coroutines.launch

@Composable
fun ScanScreen(onBack: () -> Unit) {
    val viewModel = artikoViewModel { api -> ScanViewModel(api) }
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val controller = rememberCameraController(CameraController.IMAGE_CAPTURE)
    val gallery = rememberLauncherForActivityResult(PickVisualMedia()) { uri ->
        uri?.let { scope.launch { viewModel.scan(context.readMedia(it)) } }
    }

    RequirePermissions(
        permissions = listOf(Manifest.permission.CAMERA),
        rationale = "Artiko needs the camera to scan an artwork",
    ) {
        ScanPage(
            state = state,
            viewfinder = { CameraPreview(controller) },
            onScan = {
                scope.launch {
                    runCatching { controller.takePhoto(context).readMedia("image/jpeg") }
                        .onSuccess(viewModel::scan)
                }
            },
            onPick = { gallery.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly)) },
            onReset = viewModel::reset,
            onBack = onBack,
        )
    }
}
