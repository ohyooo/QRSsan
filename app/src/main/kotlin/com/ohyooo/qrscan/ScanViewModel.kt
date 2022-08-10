package com.ohyooo.qrscan

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import com.ohyooo.qrscan.util.addHistory
import com.ohyooo.qrscan.util.barcodeClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ScanViewModel(context: Application) : AndroidViewModel(context) {
    val result = MutableStateFlow("")

    private val Uri.task: Task<List<Barcode>>
        get() = barcodeClient.process(InputImage.fromFilePath(getApplication(), this))

    init {
        viewModelScope.launch {
            result.collect {
                if (it.isNotBlank()) context.addHistory(it)
            }
        }
    }

    fun handleUri(uri: Uri?) {
        uri ?: return
        uri.task.addOnSuccessListener { r ->
            r.firstOrNull()?.displayValue?.let(result::tryEmit)
        }
    }

    fun handleIntent(intent: Intent?) {
        if (intent?.type?.startsWith("image/") != true) return
        if ((intent.clipData?.itemCount ?: 0) <= 0) return

        val uri = intent.clipData?.getItemAt(0)?.uri ?: return

        uri.task
            .addOnCompleteListener { r ->
                if (r.result.isNullOrEmpty()) {
                    Toast.makeText(getApplication(), "XxxxxxxxxX", Toast.LENGTH_SHORT).show()
                } else {
                    r.result.firstOrNull()?.displayValue?.let(result::tryEmit)
                }
            }
    }
}