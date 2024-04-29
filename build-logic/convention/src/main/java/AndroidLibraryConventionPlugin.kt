import com.android.build.api.dsl.LibraryExtension
import com.roblesdotdev.convention.Extensiontype
import com.roblesdotdev.convention.configureBuildTypes
import com.roblesdotdev.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin


class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension>() {
                configureKotlinAndroid(this)

                configureBuildTypes(
                    commonExtension = this,
                    extensionType = Extensiontype.LIBRARY,
                )

                defaultConfig {
                    testInstrumentationRunner = "androidx.text.runner.AndroidJUnitRunner"
                    consumerProguardFiles("consumer-rules.pro")
                }
            }
            
            dependencies {
                "testImplementation"(kotlin("test"))
            }
        }
    }

}