package com.ohyooo.qrscan.compose

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ohyooo.qrscan.util.clearHistory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SettingUI() {
    val context: Context = LocalContext.current

    var confirm by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val coroutineScope = rememberCoroutineScope()
        Button(onClick = {
            if (confirm) {
                coroutineScope.launch { context.clearHistory() }
                confirm = false
            } else {
                confirm = true
                coroutineScope.launch {
                    delay(1000)
                    confirm = false
                }
            }
        }) {
            Row {
                Icon(Icons.Rounded.History, "")
                Icon(Icons.Rounded.ArrowForward, "")
                if (confirm) {
                    Icon(Icons.Rounded.DeleteForever, "")
                    Icon(Icons.Rounded.QuestionMark, "")
                } else {
                    Icon(Icons.Rounded.Delete, "")
                }
            }
        }
    }
}