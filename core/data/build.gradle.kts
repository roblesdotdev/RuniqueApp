plugins {
    alias(libs.plugins.runiqueapp.android.library)
}

android {
    namespace = "com.roblesdotdev.core.data"
}

dependencies {
    implementation(libs.timber)
    implementation(projects.core.domain)
    implementation(projects.core.database)
}