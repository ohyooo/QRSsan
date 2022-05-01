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
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ohyooo.qrscan.ScanViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

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

val KEY_LIST = stringPreferencesKey("list")

val Context.ds: DataStore<Preferences> by preferencesDataStore(name = "history")

private suspend fun Context.addHistory(result: String) {
    val value = getHistories().apply {
        if (contains(result)) {
            removeAt(indexOf(result))
        }
        if (size > 20) {
            removeLast()
        }
        add(result)
    }

    ds.edit { settings ->
        settings[KEY_LIST] = Json.encodeToString(value)
    }
}

private fun Context.getHistories(): MutableList<String> {
    var json = "[]"

    runBlocking {
        ds.data.map { settings ->
            val listString = settings[KEY_LIST] ?: return@map
            json = listString
        }.first()
    }
    return try {
        Json.decodeFromString(json)
    } catch (e: Exception) {
        e.printStackTrace()
        mutableListOf()
    }
}

