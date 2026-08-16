plugins {
    alias(libs.plugins.astula.library)
}

android {
    namespace = "com.noble.tests"
}

dependencies {
    implementation(libs.junit)
    implementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}