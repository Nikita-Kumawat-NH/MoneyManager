plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)

}

android {
    namespace = "com.example.moneymanager"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.moneymanager"
        minSdk = 24
        targetSdk = 35
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
}

dependencies {

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.google.firebase.auth)
    implementation(libs.firebase.firestore)

    // AndroidX and Material Design
    implementation(libs.appcompat.v161)
    implementation(libs.material.v1110)
    implementation(libs.activity.v182)
    implementation(libs.constraintlayout.v214)

    // Google Sign-In
    implementation(libs.play.services.auth)
    implementation(libs.credentials.v120)
    implementation(libs.credentials.play.services.auth.v120)

    // Charts
    implementation(libs.mpandroidchart)
    implementation(libs.firebase.database)
    implementation(libs.gridlayout)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.v115)
    androidTestImplementation(libs.espresso.core.v351)



}