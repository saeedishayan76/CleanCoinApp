plugins {
    id("shayan.android.lib")
    id("shayan.android.hilt")
}

android {
    namespace = "com.shayan.testing"
}

dependencies {
    api(libs.junit)
    api(libs.kotlinx.coroutine.test)
    api(libs.mockk.unit)
    api(libs.app.turbine)

    implementation(project(":feature:coin:data"))

}