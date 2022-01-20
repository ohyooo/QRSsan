package com.ohyooo.qrscan.compose

import android.util.Size
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.ohyooo.qrscan.ScanActivity
import com.ohyooo.qrscan.util.QrCodeAnalyzer
import java.util.concurrent.Executors

@Composable
fun CameraUI(activity: ScanActivity) {
    AndroidView(modifier = Modifier.fillMaxSize(),
        factory = { context ->
            PreviewView(context).also {
                it.post {
                    initCamera(activity, it)
                }
            }
        })
}

private fun initCamera(activity: ScanActivity, view: PreviewView) {
    val size = Size(view.width, view.height)

    val cameraSelector = CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()

    val imageAnalysis = ImageAnalysis.Builder()
        .setTargetResolution(size)
        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
        .build()

    val cameraProviderFuture = ProcessCameraProvider.getInstance(activity)
    cameraProviderFuture.addListener({
        var lastTime = 0L
        imageAnalysis.setAnalyzer(Executors.newSingleThreadExecutor(), QrCodeAnalyzer { r ->
            val now = System.currentTimeMillis()
            if (now - lastTime > 2000) {
                lastTime = now
                activity.vm.result.value = r
            }
        })

        val cameraProvider = cameraProviderFuture.get()
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(activity,
            cameraSelector,
            Preview.Builder()
                .setTargetResolution(size)
                .build()
                .apply {
                    setSurfaceProvider(view.surfaceProvider)
                },
            imageAnalysis)

    }, ContextCompat.getMainExecutor(activity))
}

