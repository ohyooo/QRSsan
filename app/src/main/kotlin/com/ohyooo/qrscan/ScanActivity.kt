package com.ohyooo.qrscan

import android.content.Intent
import android.graphics.Color
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.M
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
import android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.google.mlkit.vision.common.InputImage
import com.ohyooo.qrscan.compose.MainUI
import com.ohyooo.qrscan.util.barcodeClient

class ScanActivity : ComponentActivity() {

    val vm by viewModels<ScanViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)

        handleIntent()

        makeStatusBarTransparent()

        setContent {
            MainUI(vm)
        }
    }

    private fun makeStatusBarTransparent() {
        window.apply {
            clearFlags(FLAG_TRANSLUCENT_STATUS)
            addFlags(FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if (SDK_INT >= M) {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
            statusBarColor = Color.TRANSPARENT
        }

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleIntent()
    }

    private fun handleIntent() {
        if (intent?.type?.startsWith("image/") != true) return
        if ((intent?.clipData?.itemCount ?: 0) <= 0) return
        val data = intent?.clipData?.getItemAt(0)?.uri ?: return

        val image = InputImage.fromFilePath(this, data)
        barcodeClient.process(image)
            .addOnCompleteListener { r ->
                if (r.result.isNullOrEmpty()) {
                    Toast.makeText(this, "XxxxxxxxxX", Toast.LENGTH_SHORT).show()
                } else {
                    r.result.firstOrNull()?.displayValue?.let {
                        vm.result.value = it
                    }
                }
            }
    }
}