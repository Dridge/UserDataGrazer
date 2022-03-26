plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

repositories {
    mavenCentral()
    google()
}

group = "me.richardeldridge"
version = "1.0-SNAPSHOT"

kotlin {
    android()
    ios {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.google.android.material:material:1.5.0")
                implementation("com.squareup.okhttp3:okhttp:4.5.0")
                implementation("com.google.code.gson:gson:2.9.0")
                implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.2")
                implementation("com.squareup.retrofit2:retrofit:2.6.3")
                implementation("com.squareup.retrofit2:converter-gson:2.6.3")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
            }
        }
        val iosMain by getting
        val iosTest by getting
    }
}

android {
    compileSdkVersion(31)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(29)
        targetSdkVersion(31)
    }
    buildFeatures {
        viewBinding = true
    }
}