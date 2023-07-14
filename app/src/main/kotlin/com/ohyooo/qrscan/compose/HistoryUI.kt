package com.ohyooo.qrscan.compose

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.ohyooo.qrscan.util.KEY_LIST
import com.ohyooo.qrscan.util.ds
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.util.regex.Pattern

@Composable
fun HistoryUI() {
    val context: Context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val results = remember {
        mutableStateListOf<String>()
    }

    LaunchedEffect(coroutineScope) {
        context.ds.data.collect {
            val listString = it[KEY_LIST] ?: return@collect
            results.clear()
            results.addAll(Json.decodeFromString(listString))
        }
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(results) { result ->
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                val annotatedLinkString = remember(result) {
                    val pattern = Pattern.compile("(?:(?:https?|ftp)://)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)")
                    val matcher = pattern.matcher(result)
                    val builder = AnnotatedString.Builder(result)

                    while (matcher.find()) {
                        val urlString = matcher.group(0)
                        builder.addStyle(
                            SpanStyle(textDecoration = TextDecoration.Underline, color = Color.Blue),
                            matcher.start(),
                            matcher.end()
                        )
                        builder.addStringAnnotation(
                            tag = "URL",
                            annotation = urlString,
                            start = matcher.start(),
                            end = matcher.end()
                        )
                    }
                    builder.toAnnotatedString()
                }

                ClickableText(
                    text = annotatedLinkString,
                    onClick = { offset ->
                        annotatedLinkString.getStringAnnotations("URL", offset, offset).firstOrNull()?.let {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.item))
                            context.startActivity(intent)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
