package gradle.plugins

import com.android.build.gradle.AppPlugin
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.getByType

open class BasePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.all {
            when (this) {
                is JavaPlugin -> {
                    target.extensions.getByType<JavaPluginExtension>().apply {
                        configureJava()
                    }
                }
                is LibraryPlugin -> {
                    target.configureRepositories()
                        .implementationDependenciesFrom(kotlinList + asyncAndroidList)
                }
                is AppPlugin -> {
                    target.configureRepositories()
                        .implementationDependenciesFrom(kotlinList + asyncAndroidList)
                }
            }
        }
    }
}
