package plugins

import com.android.build.api.dsl.LibraryExtension
import extensions.configureBuildTypes
import extensions.versionCatalog
import extensions.configureAndroidKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies


class AndroidLibConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.devtools.ksp")

                // apply("kotlin-android")
            }

            extensions.configure<LibraryExtension> {
                configureAndroidKotlin(this)
                defaultConfig.targetSdk = 34
                configureBuildTypes(this)
            }
            dependencies {
                "implementation"(versionCatalog().findLibrary("androidx.ktx.core").get())
                "implementation"(
                    versionCatalog().findLibrary("androidx.lifecycle.runtime.ktx").get()
                )
            }
        }
    }


}