// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.top.app.gradle.plugin) apply false
    alias(libs.plugins.android.top.library.plugin) apply false
    alias(libs.plugins.android.top.kotlin.plugin) apply false
    alias(libs.plugins.hilt.app) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.ksp) apply false
}