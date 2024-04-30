plugins {
    alias(libs.plugins.runiqueapp.android.library)
}

android {
    namespace = "com.roblesdotdev.run.location"
}

dependencies {
    implementation(libs.androidx.core.ktx)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.google.android.gms.play.services.location)

    implementation(projects.core.domain)
    implementation(projects.run.domain)
}