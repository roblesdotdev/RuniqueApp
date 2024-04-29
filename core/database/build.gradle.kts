plugins {
    alias(libs.plugins.runiqueapp.android.library)
}

android {
    namespace = "com.roblesdotdev.core.database"
}

dependencies {
    implementation(libs.org.mongodb.bson)
    implementation(projects.core.domain)
}