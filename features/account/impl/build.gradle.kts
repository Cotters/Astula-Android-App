plugins {
    alias(libs.plugins.astula.feature)
}

android {
    namespace = "com.noble.features.account.impl"
}

dependencies {
    api(project(projectPath = ":features:account:api"))
}