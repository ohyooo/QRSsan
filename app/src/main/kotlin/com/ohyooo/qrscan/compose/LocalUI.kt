package com.ohyooo.qrscan.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ohyooo.qrscan.ScanActivity

@Composable
fun LocalUI(activity: ScanActivity) {
    OutlinedButton(onClick = {
        activity.content.launch("image/*")
    }, modifier = Modifier.fillMaxSize()) {
        Text(text = "Select")
    }

}