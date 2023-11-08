
plugins {
    id("com.android.application")

}

android {
    namespace = "com.polarys.appleitour"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.polarys.appleitour"
        minSdk = 29
        targetSdk = 34
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
   /* sourceSets.getByName("main") {
        // java.srcDir("src/main/res/layouts/activities")
        // java.srcDir("src/main/res/layouts/fragments")
        // java.srcDir("src/main/res/layouts/adapters")
        // java.srcDir("src/main/res/layouts/customviews")
        //java.srcDir("src/main/res/layouts")
        java.srcDir("src/main/res")
    }*/
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

    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.6.2")
    annotationProcessor ("android.arch.lifecycle:compiler:1.1.1")
    implementation ("android.arch.lifecycle:viewmodel:1.1.1")
    implementation ("android.arch.lifecycle:extensions:1.1.1")
    implementation("com.google.android.material:material:1.10.0")
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
    implementation("com.github.abdularis:circularimageview:1.5")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.core:core:1.2.0")
  //  implementation("com.fasterxml.jackson.core:jackson-annotations")
}