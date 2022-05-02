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
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SettingUI() {
    val context: Context = LocalContext.current

    var confirm by remember {
        mutableStateOf(State.NORMAL)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val coroutineScope = rememberCoroutineScope()
        var job: Job? = null

        Button(onClick = {
            when (confirm) {
                State.NORMAL -> {
                    confirm = State.CONFIRM
                    job?.cancel()
                    job = coroutineScope.launch {
                        delay(1500)
                        confirm = State.NORMAL
                    }
                }
                State.CONFIRM -> {
                    coroutineScope.launch { context.clearHistory() }
                    confirm = State.DELETED
                }
                State.DELETED -> {
                    job?.cancel()
                    job = coroutineScope.launch {
                        delay(1500)
                        confirm = State.NORMAL
                    }
                }
            }
        }) {
            Row {
                Icon(Icons.Rounded.History, "")
                Icon(Icons.Rounded.ArrowForward, "")
                when (confirm) {
                    State.NORMAL -> {
                        Icon(Icons.Rounded.Delete, "")
                    }
                    State.CONFIRM -> {
                        Icon(Icons.Rounded.DeleteForever, "")
                        Icon(Icons.Rounded.QuestionMark, "")
                    }
                    State.DELETED -> {
                        Icon(Icons.Rounded.Delete, "")
                        Icon(Icons.Rounded.Check, "")
                    }
                }
            }
        }
    }
}

enum class State {
    NORMAL,
    CONFIRM,
    DELETED
}