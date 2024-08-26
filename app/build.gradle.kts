plugins {
    id("shayan.android.app")
    id("shayan.android.compose")
    id("shayan.android.hilt")
}
android {
    namespace = "com.shayan.cleanWithUnitTest"
}

dependencies {
    implementation(libs.compose.navigation)
    implementation(project(":feature:coin:presentation"))
}