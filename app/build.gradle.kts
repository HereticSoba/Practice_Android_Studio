plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.appsolorzanodiego"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.appsolorzanodiego"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    viewBinding{
        enable = true
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Retrofit dependencies
    implementation("com.squareup.retrofit2:retrofit:2.9.0") // Retrofit para llamadas API
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // Convertidor de Retrofit para Gson

    // Glide dependencies
    implementation("com.github.bumptech.glide:glide:4.15.1") // Cargar y mostrar imágenes
    kapt("com.github.bumptech.glide:compiler:4.15.1") // Compiler de Glide para anotaciones

    //Corutinas
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // WorkManager dependencies
    implementation("androidx.work:work-runtime-ktx:2.11.0") // Para trabajos en segundo plano

    //Room
    implementation("androidx.room:room-runtime:2.8.4")
    kapt("androidx.room:room-compiler:2.8.4")
    implementation("androidx.room:room-ktx:2.8.4")
    testImplementation("androidx.room:room-testing:2.8.4")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}