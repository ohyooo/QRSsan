object Ext {
    const val applicationId = "com.ohyooo.qrscan"
    const val compileSdk = 32
    const val buildToolsVersion = "32.0.0"
    const val minSdk = 21
    const val targetSdk = 32
    const val versionCode = 6
    const val versionName = "2.6"
}

object Libs {
    private const val kotlin_version = "1.7.0"

    object Plugin {
        const val AGP = "com.android.tools.build:gradle:7.4.0-alpha04"
        const val KGP = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
        const val KS = "org.jetbrains.kotlin:kotlin-serialization:$kotlin_version"
    }

    object Kotlin {
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.2"
    }

    object Google {
        const val material = "com.google.android.material:material:1.6.1"
        const val barcode = "com.google.mlkit:barcode-scanning:17.0.2"

        private const val accompanistVersion = "0.24.9-beta"
        const val pager = "com.google.accompanist:accompanist-pager:$accompanistVersion"
        const val indicators = "com.google.accompanist:accompanist-pager-indicators:$accompanistVersion"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.8.0"
        const val datastore = "androidx.datastore:datastore-preferences:1.0.0"
        private const val activityVersion = "1.4.0"
        const val activity_compose = "androidx.activity:activity-compose:$activityVersion"
        const val fragment = "androidx.fragment:fragment-ktx:1.4.1"
        const val lifecycle = "androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1"
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3"
    }

    object Navigation {
        const val compose = "androidx.navigation:navigation-compose:2.4.2"
    }

    object Compose {
        const val composeVersion = "1.2.0-beta03"
        const val compiler = "androidx.compose.compiler:compiler:$composeVersion"
        const val foundation = "androidx.compose.foundation:foundation:$composeVersion"
        const val layout = "androidx.compose.foundation:foundation-layout:$composeVersion"
        const val livedata = "androidx.compose.runtime:runtime-livedata:$composeVersion"
        const val material = "androidx.compose.material:material:$composeVersion"
        const val materialIconsExtended = "androidx.compose.material:material-icons-extended:$composeVersion"
        const val runtime = "androidx.compose.runtime:runtime:$composeVersion"
        const val ui = "androidx.compose.ui:ui:$composeVersion"
    }

    object Camera {
        private const val camerax_version = "1.2.0-alpha02"
        const val camera2 = "androidx.camera:camera-camera2:${camerax_version}"
        const val lifecycle = "androidx.camera:camera-lifecycle:${camerax_version}"
        const val view = "androidx.camera:camera-view:${camerax_version}"
    }

    val plugins = listOf(Plugin.AGP, Plugin.KGP, Plugin.KS)

    val composes = listOf(Compose.compiler, Compose.foundation, Compose.livedata, Compose.material, Compose.materialIconsExtended, Compose.runtime, Compose.ui)

    val implementations = listOf(
        AndroidX.activity_compose,
        AndroidX.coreKtx,
        AndroidX.datastore,
        AndroidX.fragment,
        AndroidX.lifecycle,
        AndroidX.serialization,
        Camera.camera2,
        Camera.lifecycle,
        Camera.view,
        Google.barcode,
        Google.material,
        Google.pager,
        Google.indicators,
        Kotlin.coroutines,
        Navigation.compose,
    )

    val deps: List<String> = mutableListOf<String>().apply {
        addAll(plugins)
        addAll(composes)
        add(Navigation.compose)
        addAll(implementations)
    }
}
