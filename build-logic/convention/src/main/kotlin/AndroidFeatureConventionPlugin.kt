import extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("astula.android.library")
            pluginManager.apply("astula.android.compose")
            pluginManager.apply("astula.android.hilt")

            dependencies {
                add("implementation", project(":presentation"))
                add("implementation", libs.findLibrary("hilt-navigation-compose").get())
            }
        }
    }
}