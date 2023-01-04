package gradle.plugins
import org.gradle.api.Project

class KotlinModule : BasePlugin() {

    override fun apply(target: Project) {
        applyPlugins(target)
        super.apply(target)
        applyDependencies(target)
    }

    private fun applyDependencies(target: Project) {
        target.implementationDependenciesFrom(
            diList + asyncList
        )
    }

    private fun applyPlugins(target: Project) {
        with(target) {
            with(plugins) {
                apply(BuildPlugins.JAVA_LIBRARY)
                apply(BuildPlugins.JETBRAINS_KOTLIN_JVM)
//                apply(BuildPlugins.KOTLINX_SERIALIZATION)
            }
        }
    }
}
