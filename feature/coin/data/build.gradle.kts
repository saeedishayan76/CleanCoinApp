plugins {
   id("shayan.android.lib")
    id("shayan.android.hilt")
}

dependencies {
    implementation(project(":core:network"))
    testImplementation(project(":core:testing"))
}