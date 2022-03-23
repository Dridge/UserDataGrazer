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

