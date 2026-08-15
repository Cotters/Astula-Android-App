plugins {
    alias(libs.plugins.astula.feature)
}

android {
    namespace = "com.noble.features.upload.impl"
}

dependencies {
    api(project(":features:upload:api"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}