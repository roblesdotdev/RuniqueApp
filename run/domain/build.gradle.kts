plugins {
    alias(libs.plugins.runiqueapp.jvm.library)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)

    implementation(projects.core.domain)
}