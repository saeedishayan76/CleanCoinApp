plugins {
    id("shayan.android.lib")
    id("shayan.android.compose.library")
    id("shayan.android.hilt")
}
dependencies {
    implementation(project(":feature:coin:data"))
    implementation(project(":feature:coin:domain"))
    debugImplementation(libs.androidx.ui.tooling)
    implementation(libs.coil.kt)
    implementation(libs.coil.compose)
    implementation(libs.coil.svg)
    implementation(libs.vicoChart.compose)
    implementation(libs.vicoChart.core)

    testImplementation(project(":core:testing"))
}