plugins {
    alias(libs.plugins.runiqueapp.android.library)
}

android {
    namespace = "com.roblesdotdev.run.network"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.data)
}