plugins {
    alias(libs.plugins.runiqueapp.android.library)
    alias(libs.plugins.runiqueapp.jvm.ktor)
}

android {
    namespace = "com.roblesdotdev.run.network"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.data)
}