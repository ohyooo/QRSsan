plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlinx-serialization")
}

android {
    signingConfigs {
        getByName("debug") {
            storeFile = file("..\\signkey.jks")
            storePassword = "123456"
            keyPassword = "123456"
            keyAlias = "demo"

            enableV3Signing = true
            enableV4Signing = true
        }
    }
    namespace = Ext.applicationId
    compileSdk = Ext.compileSdk
    buildToolsVersion = Ext.buildToolsVersion
    defaultConfig {
        applicationId = Ext.applicationId
        minSdk = Ext.minSdk
        targetSdk = Ext.targetSdk
        versionCode = Ext.versionCode
        versionName = Ext.versionName + hashTag
        proguardFile("proguard-rules.pro")
        signingConfig = signingConfigs.getByName("debug")
    }
    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
        }
        create("benchmark") {
            initWith(getByName("release"))
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf("release")
            isDebuggable = false

            // https://developer.android.com/topic/performance/baselineprofiles/create-baselineprofile
            // Benchmark builds should not be obfuscated.
            postprocessing {
                isRemoveUnusedCode = true
                isRemoveUnusedResources = true
                isObfuscate = false
                isOptimizeCode = true
            }
        }
    }
    splits {
        abi {
            isEnable = true
            reset()
            include("x86", "armeabi-v7a", "arm64-v8a", "x86_64")
            isUniversalApk = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
        // Disable unused AGP features
        buildConfig = false
        aidl = false
        renderScript = false
        resValues = false
        shaders = false
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Libs.Compose.compilerVersion
    }
}

dependencies {
    Libs.implementList.forEach(::implementation)
    Libs.debugImplementList.forEach(::debugImplementation)
}

val hashTag: String
    get() {
        if (!File(rootDir.path + "/.git").exists()) return ""
        return ProcessBuilder(listOf("git", "rev-parse", "--short", "HEAD"))
            .directory(rootDir)
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)
            .start()
            .apply { waitFor(5, TimeUnit.SECONDS) }
            .run {
                val error = errorStream.bufferedReader().readText().trim()
                if (error.isNotEmpty()) {
                    ""
                } else {
                    "-" + inputStream.bufferedReader().readText().trim()
                }
            }
    }
