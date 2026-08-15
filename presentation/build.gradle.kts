plugins {
    alias(libs.plugins.astula.library)
    alias(libs.plugins.astula.compose)
    alias(libs.plugins.astula.serialization)
}

android {
    namespace = "com.noble.presentation"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.navigation3.runtime)
}