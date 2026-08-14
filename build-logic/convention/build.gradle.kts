plugins {
    `kotlin-dsl`
}

group = "com.noble.buildlogic"

dependencies {
    compileOnly(libs.android.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = "astula.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidCompose") {
            id = "astula.android.compose"
            implementationClass = "ComposeConventionPlugin"
        }

        register("androidHilt") {
            id = "astula.android.hilt"
            implementationClass = "HiltConventionPlugin"
        }

        register("androidFeature") {
            id = "astula.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
    }
}