plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.runiqueapp.jvm.ktor)
}

android {
    namespace = "com.roblesdotdev.auth.data"
}

dependencies {
    implementation(projects.auth.domain)
    implementation(projects.core.domain)
    implementation(projects.core.data)
}