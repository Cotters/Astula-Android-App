import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.Actions.with
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureApiConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("astula.android.library")
            pluginManager.apply("astula.kotlin.serialization")
            pluginManager.apply("astula.android.hilt")

            dependencies {
            add("implementation", libs.findLibrary("androidx-navigation3-ui").get())
            }
        }
    }
}