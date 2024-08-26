plugins {
    id("shayan.android.lib")
    id("shayan.android.hilt")
}

android {
    namespace = "com.shauan.domain"
}
dependencies {
    implementation(project(":feature:coin:data"))
    testImplementation(project(":core:testing"))
}