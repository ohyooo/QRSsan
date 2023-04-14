object Ext {
    const val applicationId = "com.ohyooo.qrscan"
    const val compileSdk = 33
    const val buildToolsVersion = "33.0.2"
    const val minSdk = 21
    const val targetSdk = 33
    const val versionCode = 8
    const val versionName = "2.8"
}

object Libs {
    val updateList = arrayListOf<String>()
    val implementList = arrayListOf<String>()
    val debugImplementList = arrayListOf<String>()

    private const val kotlin_version = "1.8.20"

    object Plugin {
        val AGP = "com.android.tools.build:gradle:8.0.0".regUpdate()
        val KGP = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version".regUpdate()
        val KS = "org.jetbrains.kotlin:kotlin-serialization:$kotlin_version".regUpdate()
    }

    object Kotlin {
        val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4".regLib()
    }

    object Google {
        val material = "com.google.android.material:material:1.8.0".regLib()
        val barcode = "com.google.mlkit:barcode-scanning:17.1.0".regLib()

        private const val accompanistVersion = "0.30.1"
        val pager = "com.google.accompanist:accompanist-pager:$accompanistVersion".regLib()
        val indicators = "com.google.accompanist:accompanist-pager-indicators:$accompanistVersion".regLib()
    }

    object AndroidX {
        val coreKtx = "androidx.core:core-ktx:1.10.0".regLib()
        val datastore = "androidx.datastore:datastore-preferences:1.0.0".regLib()
        val activity_compose = "androidx.activity:activity-compose:1.7.0".regLib()
        val fragment = "androidx.fragment:fragment-ktx:1.5.6".regLib()
        val lifecycle = "androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1".regLib()
        val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0".regLib()
        val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4".regLib()
        val profileinstaller = "androidx.profileinstaller:profileinstaller:1.3.0".regLib()
    }

    object Navigation {
        val compose = "androidx.navigation:navigation-compose:2.5.3".regUpdate()
    }

    object Compose {
        val composeVersion = "1.4.1"
        val compilerVersion = "1.4.5"
        val compiler = "androidx.compose.compiler:compiler:$compilerVersion".regLib()
        val foundation = "androidx.compose.foundation:foundation:$composeVersion".regLib()
        val material = "androidx.compose.material:material:$composeVersion".regLib()
        val materialIconsExtended = "androidx.compose.material:material-icons-extended:$composeVersion".regLib()
        val runtime = "androidx.compose.runtime:runtime:$composeVersion".regLib()
        val ui = "androidx.compose.ui:ui:$composeVersion".regLib()
    }

    object Camera {
        private const val camerax_version = "1.3.0-alpha04"
        val camera2 = "androidx.camera:camera-camera2:${camerax_version}".regLib()
        val lifecycle = "androidx.camera:camera-lifecycle:${camerax_version}".regLib()
        val view = "androidx.camera:camera-view:${camerax_version}".regLib()
    }

    object Test {
        val junit = "androidx.test.ext:junit:1.1.5".regUpdate()
        val espresso = "androidx.test.espresso:espresso-core:3.5.1".regUpdate()
        val uiautomator = "androidx.test.uiautomator:uiautomator:2.2.0".regUpdate()
        val macro = "androidx.benchmark:benchmark-macro-junit4:1.2.0-alpha06".regUpdate()
    }

    init {
        Plugin
        Kotlin
        Compose
        Camera
        AndroidX
        Google
        Test
    }

    private fun String.regLib() = this.also {
        implementList.add(it)
        updateList.add(it)
    }
    fun String.regDebug() = this.also {
        debugImplementList.add(it)
        updateList.add(it)
    }
    private fun String.regUpdate() = this.also(updateList::add)
}
