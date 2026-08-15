plugins {
    id("astula.android.library")
}

android {
    namespace = "com.noble.features.account.api"
}

dependencies {
    implementation(libs.androidx.navigation3.ui)
}