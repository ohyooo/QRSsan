object Ext {
    const val applicationId = "com.ohyooo.qrscan"
    const val compileSdk = 34
    const val minSdk = 21
    const val targetSdk = 34
    const val versionCode = 10
    const val versionName = "2.10"
}

object Libs {
    val updateList = arrayListOf<String>()
    val implementList = arrayListOf<String>()
    val debugImplementList = arrayListOf<String>()

    object Version {
        const val agp = "8.0.2"
        const val kotlin = "1.8.22"
    }

    object Plugin {
        val AGP = "com.android.tools.build:gradle:${Version.agp}".regUpdate()
        val KGP = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}".regUpdate()
        val KS = "org.jetbrains.kotlin:kotlin-serialization:${Version.kotlin}".regUpdate()
    }

    object Kotlin {
        val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.2".regLib()
    }

    object Google {
        val material = "com.google.android.material:material:1.9.0".regLib()
        val barcode = "com.google.mlkit:barcode-scanning:17.1.0".regLib()

        private const val accompanistVersion = "0.30.1"
        val pager = "com.google.accompanist:accompanist-pager:$accompanistVersion".regLib()
        val indicators = "com.google.accompanist:accompanist-pager-indicators:$accompanistVersion".regLib()
    }

    object AndroidX {
        val coreKtx = "androidx.core:core-ktx:1.10.1".regLib()
        val datastore = "androidx.datastore:datastore-preferences:1.0.0".regLib()
        val activity_compose = "androidx.activity:activity-compose:1.7.2".regLib()
        val fragment = "androidx.fragment:fragment-ktx:1.6.0".regLib()
        val lifecycle = "androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1".regLib()
        val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1".regLib()
        val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2".regLib()
        val profileinstaller = "androidx.profileinstaller:profileinstaller:1.3.1".regLib()
    }

    object Navigation {
        val compose = "androidx.navigation:navigation-compose:2.6.0".regUpdate()
    }

    object Compose {
        val composeVersion = "1.4.3"
        val compilerVersion = "1.4.8"
        val compiler = "androidx.compose.compiler:compiler:$compilerVersion".regLib()
        val foundation = "androidx.compose.foundation:foundation:$composeVersion".regLib()
        val material = "androidx.compose.material:material:$composeVersion".regLib()
        val materialIconsExtended = "androidx.compose.material:material-icons-extended:$composeVersion".regLib()
        val runtime = "androidx.compose.runtime:runtime:$composeVersion".regLib()
        val ui = "androidx.compose.ui:ui:$composeVersion".regLib()
        val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion".regLib()
        val uiTooling = "androidx.compose.ui:ui-tooling:$composeVersion".regDebug()
    }

    object Camera {
        private const val camerax_version = "1.3.0-beta01"
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
