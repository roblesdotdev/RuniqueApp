plugins {
    alias(libs.plugins.runiqueapp.jvm.library)
}

dependencies {
    implementation(projects.core.domain)
}