plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        ndk {
            abiFilters("armeabi") // "x86", "armeabi-v7a", "x86_64", "arm64-v8a"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    lintOptions {
        isAbortOnError = false
    }

    androidExtensions {
        isExperimental = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin(module = "stdlib-jdk7", version = "1.3.61"))
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("androidx.core:core-ktx:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test:runner:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")
    implementation("com.squareup.okhttp3:okhttp:3.12.3")
    implementation("com.squareup.okhttp3:logging-interceptor:3.9.1")
    implementation("com.squareup.retrofit2:retrofit:2.6.1")
    implementation("com.squareup.retrofit2:converter-gson:2.6.1")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.6.1")
    implementation("io.reactivex.rxjava2:rxjava:2.2.10")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation("io.reactivex.rxjava2:rxkotlin:2.4.0")
    implementation("com.github.bumptech.glide:glide:4.10.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.9.0")
    implementation("com.scwang.smartrefresh:SmartRefreshLayout:1.1.0")
//    implementation("com.github.CymChad:BaseRecyclerViewAdapterHelper:2.9.47")
    implementation("com.gyf.barlibrary:barlibrary:2.3.0")
    implementation("me.jessyan:autosize:1.1.2")
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.1")
    implementation("com.tencent.bugly:crashreport:3.0.0")
    implementation("com.tencent.bugly:nativecrashreport:3.7.1")
}