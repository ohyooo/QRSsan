package com.ohyooo.qrscan.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ohyooo.qrscan.ScanViewModel

@Composable
fun EditUI(
    vm: ScanViewModel,
    onTextChange: (String) -> Unit = {}
) {
    var editText by remember { mutableStateOf("") }
    var lastText by remember { mutableStateOf("") }

    LaunchedEffect(vm) {
        vm.result.collect {
            if (lastText == it) return@collect
            editText = it
            lastText = editText
        }
    }

    OutlinedTextField(
        value = editText,
        onValueChange = {
            editText = it
            onTextChange(it)
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    )
}
