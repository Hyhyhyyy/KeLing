plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

import java.util.Properties
import java.io.FileInputStream

android {
    namespace = "com.keling.app"
    compileSdk = 34

    // Read local.properties for Qwen credentials
    val localProps = Properties().apply {
        val lpFile = rootProject.file("local.properties")
        if (lpFile.exists()) {
            FileInputStream(lpFile).use { load(it) }
        }
    }

    defaultConfig {
        applicationId = "com.keling.app"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"

        // Qwen API config from local.properties
        val qwenApiKey = (localProps.getProperty("QWEN_API_KEY") ?: "").trim()
        val qwenBaseUrl = (localProps.getProperty("QWEN_BASE_URL") ?: "https://dashscope.aliyuncs.com/").trim()
        buildConfigField("String", "QWEN_API_KEY", "\"$qwenApiKey\"")
        buildConfigField("String", "QWEN_BASE_URL", "\"$qwenBaseUrl\"")

        // 注入 local.properties 定义的多厂商 Keys
        val keyQwen = localProps.getProperty("KEY_QWEN") ?: ""
        val keyDoubao = localProps.getProperty("KEY_DOUBAO") ?: ""
        val keyMoonshot = localProps.getProperty("KEY_MOONSHOT") ?: ""
        val keyGemini = localProps.getProperty("KEY_GEMINI") ?: ""
        val keyOpenai = localProps.getProperty("KEY_OPENAI") ?: ""
        val keyDeepseek = localProps.getProperty("KEY_DEEPSEEK") ?: ""

        buildConfigField("String", "KEY_QWEN", "\"$keyQwen\"")
        buildConfigField("String", "KEY_DOUBAO", "\"$keyDoubao\"")
        buildConfigField("String", "KEY_MOONSHOT", "\"$keyMoonshot\"")
        buildConfigField("String", "KEY_GEMINI", "\"$keyGemini\"")
        buildConfigField("String", "KEY_OPENAI", "\"$keyOpenai\"")
        buildConfigField("String", "KEY_DEEPSEEK", "\"$keyDeepseek\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

// Room schema 导出目录配置（配合 exportSchema = true 使用）
ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}

dependencies {
    // Core Android
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.1")

    // Compose BOM
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.animation:animation")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.5")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    ksp("com.google.dagger:hilt-compiler:2.48")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Coil for image loading
    implementation("io.coil-kt:coil-compose:2.5.0")

    // Lottie for animations
    implementation("com.airbnb.android:lottie-compose:6.2.0")

    // Charts
    implementation("com.patrykandpatrick.vico:compose-m3:1.13.1")

    // Biometric
    implementation("androidx.biometric:biometric:1.1.0")

    // CameraX for OCR
    implementation("androidx.camera:camera-camera2:1.3.0")
    implementation("androidx.camera:camera-lifecycle:1.3.0")
    implementation("androidx.camera:camera-view:1.3.0")

    // ML Kit for OCR
    implementation("com.google.mlkit:text-recognition-chinese:16.0.0")

    // ExoPlayer for media
    implementation("androidx.media3:media3-exoplayer:1.2.0")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.10.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

// IDE sync expects this task in some setups
if (tasks.findByName("prepareKotlinBuildScriptModel") == null) {
    tasks.register("prepareKotlinBuildScriptModel") {}
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "17"
    }
}
