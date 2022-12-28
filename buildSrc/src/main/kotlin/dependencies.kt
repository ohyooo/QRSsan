object Ext {
    const val applicationId = "com.ohyooo.qrscan"
    const val compileSdk = 33
    const val buildToolsVersion = "33.0.1"
    const val minSdk = 21
    const val targetSdk = 33
    const val versionCode = 6
    const val versionName = "2.6"
}

object Libs {
    private const val kotlin_version = "1.7.21"

    object Plugin {
        const val AGP = "com.android.tools.build:gradle:7.3.1"
        const val KGP = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
        const val KS = "org.jetbrains.kotlin:kotlin-serialization:$kotlin_version"

        val list = arrayOf(AGP, KGP, KS)
    }

    object Kotlin {
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4"

        val list = arrayOf(coroutines)
    }

    object Google {
        const val material = "com.google.android.material:material:1.7.0"
        const val barcode = "com.google.mlkit:barcode-scanning:17.0.3"

        private const val accompanistVersion = "0.28.0"
        const val pager = "com.google.accompanist:accompanist-pager:$accompanistVersion"
        const val indicators = "com.google.accompanist:accompanist-pager-indicators:$accompanistVersion"

        val list = arrayOf(material, barcode, pager, indicators)
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.9.0"
        const val datastore = "androidx.datastore:datastore-preferences:1.0.0"
        const val activity_compose = "androidx.activity:activity-compose:1.6.1"
        const val fragment = "androidx.fragment:fragment-ktx:1.5.5"
        const val lifecycle = "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1"
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4"
        const val profileinstaller = "androidx.profileinstaller:profileinstaller:1.2.1"

        val list = arrayOf(coreKtx, datastore, activity_compose, fragment, lifecycle, serialization, coroutines, profileinstaller)
    }

    object Test {
        const val junit = "androidx.test.ext:junit:1.1.3"
        const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
        const val uiautomator = "androidx.test.uiautomator:uiautomator:2.2.0"
        const val macro = "androidx.benchmark:benchmark-macro-junit4:1.2.0-alpha06"

        val list = arrayOf(junit, espresso, uiautomator, macro)
    }

    object Navigation {
        const val compose = "androidx.navigation:navigation-compose:2.5.3"

        val list = arrayOf(compose)
    }

    object Compose {
        const val composeVersion = "1.4.0-alpha03"
        const val compilerVersion = "1.4.0-alpha02"
        const val compiler = "androidx.compose.compiler:compiler:$compilerVersion"
        const val foundation = "androidx.compose.foundation:foundation:$composeVersion"
        const val layout = "androidx.compose.foundation:foundation-layout:$composeVersion"
        const val livedata = "androidx.compose.runtime:runtime-livedata:$composeVersion"
        const val material = "androidx.compose.material:material:$composeVersion"
        const val materialIconsExtended = "androidx.compose.material:material-icons-extended:$composeVersion"
        const val runtime = "androidx.compose.runtime:runtime:$composeVersion"
        const val ui = "androidx.compose.ui:ui:$composeVersion"

        val list = arrayOf(compiler, foundation, layout, livedata, material, materialIconsExtended, runtime, ui)
    }

    object Camera {
        private const val camerax_version = "1.3.0-alpha02"
        const val camera2 = "androidx.camera:camera-camera2:${camerax_version}"
        const val lifecycle = "androidx.camera:camera-lifecycle:${camerax_version}"
        const val view = "androidx.camera:camera-view:${camerax_version}"

        val list = arrayOf(camera2, lifecycle, view)
    }

    val composes = listOf(Compose.compiler, Compose.foundation, Compose.livedata, Compose.material, Compose.materialIconsExtended, Compose.runtime, Compose.ui)

    val implementations = AndroidX.list + Camera.list + Google.list + Kotlin.list + Navigation.list

    val deps: List<String> = mutableListOf<String>().apply {
        addAll(Plugin.list)
        addAll(Compose.list)
        addAll(Navigation.list)
        addAll(implementations)
    }
}
