plugins {
    `kotlin-dsl`
}

group = "com.noble.buildlogic"

dependencies {
    compileOnly(libs.android.gradle.plugin)
    implementation(libs.kotlin.serialization.plugin)
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

        register("serialization") {
            id = "astula.kotlin.serialization"
            implementationClass = "SerializationConventionPlugin"
        }

        register("androidHilt") {
            id = "astula.android.hilt"
            implementationClass = "HiltConventionPlugin"
        }

        register("androidFeatureApi") {
            id = "astula.android.feature.api"
            implementationClass = "AndroidFeatureApiConventionPlugin"
        }

        register("androidFeature") {
            id = "astula.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
    }
}