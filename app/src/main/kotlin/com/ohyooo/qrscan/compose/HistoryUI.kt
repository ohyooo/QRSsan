package com.ohyooo.qrscan.compose

import android.content.Context
import android.text.util.Linkify
import android.widget.TextView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.ohyooo.qrscan.ScanViewModel
import com.ohyooo.qrscan.util.addHistory
import com.ohyooo.qrscan.util.getHistories
import kotlinx.coroutines.runBlocking

@Composable
fun HistoryUI(vm: ScanViewModel) {
    val context: Context = LocalContext.current
    val results = remember {
        mutableStateListOf<String>().apply { addAll(context.getHistories()) }
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(results.size) { index ->
            val result = results[index]
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                AndroidView(
                    modifier = Modifier.fillMaxWidth(),
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
        runBlocking {
            if (it.isBlank() || it == results.lastOrNull()) return@runBlocking
            results.clear()
            results.addAll(context.addHistory(it))
        }
    }
}

