plugins {
    id("astula.android.feature")
}

android {
    namespace = "com.noble.features.account.impl"
}

dependencies {
    api(project(projectPath = ":features:account:api"))

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}