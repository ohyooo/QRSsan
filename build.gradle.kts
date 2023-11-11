@file:Suppress("UnstableApiUsage")

plugins {
    alias(libs.plugins.agp) apply false
    alias(libs.plugins.kgp) apply false
    alias(libs.plugins.ks) apply false
}

allprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-Xbackend-threads=12", "-Xcontext-receivers", "-jvm-target=17"
            )
        }
    }
}

