package com.ohyooo.qrscan.compose

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ohyooo.qrscan.ScanViewModel

@Composable
fun LocalUI(vm: ScanViewModel) {

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent(), vm::handleUri)

    OutlinedButton(onClick = {
        launcher.launch("image/*")
    }, modifier = Modifier.fillMaxSize()) {
        Text(text = "Select")
    }

}