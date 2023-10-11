
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
    sourceSets {
        named("main") {
            manifest.srcFile("src/main/AndroidManifest.xml")
            java.srcDirs("src/main/java", "apt_generated")
            aidl.srcDirs("src/main.aidl", "apt_generated")
            assets.srcDirs("src/main/assets")
            res.srcDirs(
                    "src/main/res/layout/activity",
                    "src/main/res/layout/fragment",
                    "src/main/res/layout/dialog",
                    "src/main/res/layout/adapter",
                    "src/main/res"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures{
        dataBinding = true
        viewBinding = true
    }
}

dependencies {

    annotationProcessor ("android.arch.lifecycle:compiler:1.1.1")
    implementation ("android.arch.lifecycle:viewmodel:1.1.0")
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
    implementation("com.squareup.picasso:picasso:2.71828")
    implementation("com.fasterxml.jackson.core:jackson-annotations")
}