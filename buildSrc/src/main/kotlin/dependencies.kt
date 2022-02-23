object Ext {
    const val applicationId = "com.ohyooo.qrscan"
    const val compileSdk = 32
    const val buildToolsVersion = "32.0.0"
    const val minSdk = 21
    const val targetSdk = 32
    const val versionCode = 5
    const val versionName = "2.3"
}

object Libs {
    private const val kotlin_version = "1.6.10"

    object Plugin {
        const val AGP = "com.android.tools.build:gradle:7.3.0-alpha03"
        const val KGP = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    }

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0"
    }

    object Google {
        const val material = "com.google.android.material:material:1.6.0-alpha02"
        const val barcode = "com.google.mlkit:barcode-scanning:17.0.2"

        private const val accompanistVersion = "0.24.2-alpha"
        const val pager = "com.google.accompanist:accompanist-pager:$accompanistVersion"
        const val indicators = "com.google.accompanist:accompanist-pager-indicators:$accompanistVersion"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.8.0-alpha04"
        const val datastore = "androidx.datastore:datastore:1.0.0"
        private const val activityVersion = "1.5.0-alpha02"
        const val activity_compose = "androidx.activity:activity-compose:$activityVersion"
        const val fragment = "androidx.fragment:fragment-ktx:1.5.0-alpha02"
        const val lifecycle = "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.0-alpha02"
    }

    object Navigation {
        const val compose = "androidx.navigation:navigation-compose:2.5.0-alpha02"
    }

    object Compose {
        const val composeVersion = "1.2.0-alpha03"
        const val animation = "androidx.compose.animation:animation:$composeVersion"
        const val compiler = "androidx.compose.compiler:compiler:$composeVersion"
        const val foundation = "androidx.compose.foundation:foundation:$composeVersion"
        const val layout = "androidx.compose.foundation:foundation-layout:$composeVersion"
        const val livedata = "androidx.compose.runtime:runtime-livedata:$composeVersion"
        const val material = "androidx.compose.material:material:$composeVersion"
        const val materialIconsExtended = "androidx.compose.material:material-icons-extended:$composeVersion"
        const val runtime = "androidx.compose.runtime:runtime:$composeVersion"
        const val tooling = "androidx.compose.ui:ui-tooling:$composeVersion"
        const val ui = "androidx.compose.ui:ui:$composeVersion"
    }

    object Camera {
        private const val camerax_version = "1.1.0-alpha11"
        const val camera2 = "androidx.camera:camera-camera2:${camerax_version}"
        const val lifecycle = "androidx.camera:camera-lifecycle:${camerax_version}"
        const val view = "androidx.camera:camera-view:1.1.0-beta01"
    }

    val plugins = listOf(Plugin.AGP, Plugin.KGP)

    val composes = listOf(Compose.animation, Compose.compiler, Compose.foundation, Compose.layout, Compose.livedata, Compose.material, Compose.materialIconsExtended, Compose.runtime, Compose.tooling, Compose.ui)

    val implementations = listOf(
        AndroidX.activity_compose,
        AndroidX.coreKtx,
        AndroidX.datastore,
        AndroidX.fragment,
        AndroidX.lifecycle,
        Camera.camera2,
        Camera.lifecycle,
        Camera.view,
        Google.barcode,
        Google.material,
        Google.pager,
        Google.indicators,
        Kotlin.coroutines,
        Kotlin.stdlib,
        Navigation.compose,
    )

    val deps: List<String> = mutableListOf<String>().apply {
        addAll(plugins)
        addAll(composes)
        add(Navigation.compose)
        addAll(implementations)
    }
}
