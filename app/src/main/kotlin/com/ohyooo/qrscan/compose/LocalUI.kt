package com.ohyooo.qrscan.compose

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.mlkit.vision.common.InputImage
import com.ohyooo.qrscan.ScanViewModel
import com.ohyooo.qrscan.util.barcodeClient

@Composable
fun LocalUI(vm: ScanViewModel) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri ?: return@rememberLauncherForActivityResult
        val image = InputImage.fromFilePath(context, uri)

        barcodeClient.process(image)
            .addOnSuccessListener { r ->
                r.firstOrNull()?.displayValue?.let {
                    vm.result.value = it
                }
            }
    }

    OutlinedButton(onClick = {
        launcher.launch("image/*")
    }, modifier = Modifier.fillMaxSize()) {
        Text(text = "Select")
    }

}