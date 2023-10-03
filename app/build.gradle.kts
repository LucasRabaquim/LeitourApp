
plugins {
    id("com.android.application")
}

android {
    namespace = "com.polarys.appleitour"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.polarys.appleitour"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation ("android.arch.lifecycle:extensions:1.1.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.code.gson:gson:2.10.1")
}