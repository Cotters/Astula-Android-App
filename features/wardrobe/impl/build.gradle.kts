plugins {
    alias(libs.plugins.astula.feature)
}

android {
    namespace = "com.noble.features.wardrobe.impl"
}

dependencies {
    api(project(projectPath = ":features:wardrobe:api"))

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}