package com.roblesdotdev.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.run {
        buildFeatures {
            compose = true
        }

        composeOptions {
            val composeCompiler = libs.findVersion("composeCompiler").get().toString()
            kotlinCompilerExtensionVersion = composeCompiler
        }

        dependencies {
            val bom = libs.findLibrary("androidx-compose.bom").get()
            val composePreview = libs.findLibrary("androidx.ui.tooling.preview").get()
            "implementation"(platform(bom))
            "androidTestImplementation"(platform(bom))
            "debugImplementation"(composePreview)
        }
    }
}