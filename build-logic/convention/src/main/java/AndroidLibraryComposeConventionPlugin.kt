import com.android.build.api.dsl.LibraryExtension
import com.roblesdotdev.convention.Extensiontype
import com.roblesdotdev.convention.configureAndroidCompose
import com.roblesdotdev.convention.configureBuildTypes
import com.roblesdotdev.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.kotlin


class AndroidLibraryComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("runiqueapp.android.library")
            }

            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidCompose(extension)
        }
    }

}