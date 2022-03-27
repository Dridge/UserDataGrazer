plugins {
    id("com.android.application")
    kotlin("android")
}

group = "me.richardeldridge"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.5.0")
    implementation("com.squareup.okhttp3:okhttp:4.5.0")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("com.squareup.picasso:picasso:2.5.2")
    //    //https://github.com/mannodermaus/android-junit5
    // (Required) Writing and executing Unit Tests on the JUnit Platform
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

android {
    compileSdkVersion(31)
    defaultConfig {
        applicationId = "me.richardeldridge.androidApp"
        minSdkVersion(29)
        targetSdkVersion(31)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

}
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

