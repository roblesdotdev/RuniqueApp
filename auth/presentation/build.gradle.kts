plugins {
    alias(libs.plugins.runiqueapp.android.feature.ui)
}

android {
    namespace = "com.roblesdotdev.auth.presentation"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.auth.domain)
}