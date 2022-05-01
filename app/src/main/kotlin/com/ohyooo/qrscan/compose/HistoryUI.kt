package com.ohyooo.qrscan.compose

import android.content.Context
import android.text.util.Linkify
import android.widget.TextView
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.ohyooo.qrscan.ScanViewModel
import com.ohyooo.qrscan.util.addHistory
import com.ohyooo.qrscan.util.getHistories
import kotlinx.coroutines.launch

@Composable
fun HistoryUI(vm: ScanViewModel) {
    val context: Context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    context.getHistories().forEach { result ->
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                AndroidView(modifier = Modifier.fillMaxSize(),
                    factory = { context ->
                        TextView(context).apply {
                            autoLinkMask = Linkify.ALL
                            setTextIsSelectable(true)
                            text = result
                        }
                    },
                    update = {
                        it.text = result
                    })
            }
        }
    }

    vm.result.observe(LocalLifecycleOwner.current) {
        if (it.isBlank()) return@observe
        coroutineScope.launch {
            context.addHistory(it)
        }
    }
}

