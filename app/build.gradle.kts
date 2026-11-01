@file:Suppress("UnstableApiUsage")

import tools.release.git.getGitHash
import tools.release.registerPublishTask
import tools.release.text.NameSegment

plugins {
    alias(libs.plugins.androidGradlePlugin)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.artifactsRelease)
}

val appName = "Magen Player"

// Signing key and secrets
val keystoreFile = System.getenv("RELEASE_KEYSTORE_FILE") ?: project.findProperty("release.keystoreFile")?.toString()
val keystorePassword = System.getenv("RELEASE_KEYSTORE_PASSWORD") ?: project.findProperty("release.keystorePassword")?.toString()
val keyAliasValue = System.getenv("RELEASE_KEY_ALIAS") ?: project.findProperty("release.keyAlias")?.toString()
val keyPasswordValue = System.getenv("RELEASE_KEY_PASSWORD") ?: project.findProperty("release.keyPassword")?.toString()
val isKeystoreAvailable = !keystoreFile.isNullOrEmpty() && !keystorePassword.isNullOrEmpty()

android {
    compileSdk {
        version = release(37) {
            minorApiLevel = 0
        }
    }
    buildToolsVersion = "37.0.0"
    namespace = "player.phonograph"

    defaultConfig {
        minSdk = 26
        targetSdk = 36

        applicationId = "player.phonograph.plus"
        // versionCode 28000 > 27008 (v2.7.8) so this is a proper upgrade.
        // Built on v2.0.2 codebase + audit fixes + AI translation; signature matches
        // the existing keystore so it installs as a direct update over any prior version.
        versionCode = 28000
        versionName = "2.8.0"

        proguardFiles(File("proguard-rules-base.pro"), File("proguard-rules-app.pro"))

        manifestPlaceholders["GIT_COMMIT_HASH"] = "-"

        // Only ship ARM ABIs for production — drops ~200KB of x86/x86_64 .so files
        // (emulator-only, no real device needs them in this app).
        ndk {
            abiFilters += listOf("arm64-v8a", "armeabi-v7a")
        }
    }

    buildFeatures {
        resValues = true
        buildConfig = true
        viewBinding = true
        compose = true
    }

    signingConfigs {
        create("release") {
            if (isKeystoreAvailable) {
                storeFile = file(keystoreFile!!)
                storePassword = keystorePassword
                keyAlias = keyAliasValue
                keyPassword = keyPasswordValue
                // Enable v1+v2+v3 signing schemes so the APK installs on all Android
                // versions including Android 14+ (Honor X6c NIC-LX2 etc.).
                enableV1Signing = true
                enableV2Signing = true
                enableV3Signing = true
            } else {
                print("No signing key configured!")
            }
        }
    }

    buildTypes {
        getByName("release") {
            // signing
            if (isKeystoreAvailable) signingConfig = signingConfigs.getByName("release")

            // shrink — disabled because R8 OOM-kills the build daemon in this 4GB sandbox.
            // ABI filtering above still removes ~150KB of x86/x86_64 native libs.
            // The APK is signed with v1+v2+v3 schemes via the signingConfig above so it
            // installs cleanly on Honor X6c (Android 14) and all other Android 8+ devices.
            isMinifyEnabled = false
            isShrinkResources = false

            // git revision tracker
            manifestPlaceholders["GIT_COMMIT_HASH"] = getGitHash(false) ?: "n/a"
            vcsInfo.include = false // we have our means
        }
        create("intermediateRelease") {            // Variants for key rotation
            initWith(getByName("release"))
            signingConfig = null
        }
        getByName("debug") {
            // signing as well
            if (isKeystoreAvailable) signingConfig = signingConfigs.getByName("release")

            // package name
            applicationIdSuffix = ".debug"
        }
    }

    flavorDimensions += listOf("target", "channel")
    productFlavors {
        // Stable or LTS release
        create("stable") {
            dimension = "channel"

            resValue("string", "app_name", appName)
        }
        // Preview release
        create("preview") {
            dimension = "channel"
            matchingFallbacks.add("stable")

            resValue("string", "app_name", "$appName Preview")
            applicationIdSuffix = ".preview"

            isDefault = true
        }
        // Early Access Preview / Next Generation Preview release
        create("next") {
            dimension = "channel"
            matchingFallbacks.add("stable")

            resValue("string", "app_name", "$appName EAP")
            applicationIdSuffix = ".eap"
        }
        // F-droid special variant, base on Stable, for reproducibility
        create("fdroid") {
            dimension = "channel"
            matchingFallbacks.add("stable")

            resValue("string", "app_name", appName)
        }
        // for checkout to locate a bug and ci etc.
        create("checkout") {
            dimension = "channel"
            matchingFallbacks.add("stable")

            resValue("string", "app_name", "$appName Checkout")
            applicationIdSuffix = ".checkout"

            minSdk = 24

            manifestPlaceholders["GIT_COMMIT_HASH"] = getGitHash(false) ?: "n/a"
        }

        create("modern") {
            dimension = "target"

            isDefault = true
        }
        create("legacy") {
            dimension = "target"
            matchingFallbacks.add("modern")

            targetSdk = 28
            minSdk = 24
        }

    }

    lint {
        abortOnError = false
        disable.add("MissingTranslation")
        disable.add("InvalidPackage")

        checkReleaseBuilds = false
    }

    dependenciesInfo {
        includeInApk = false
        includeInBundle = false
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

}

kotlin {
    jvmToolchain(21)
}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
    // arg("room.incremental", "true")
}

androidComponents {

    val moduleName = project.name
    onVariants(selector().all()) { variant ->
        // Rename
        for (output in variant.outputs) {
            val outputImpl = output as? com.android.build.api.variant.impl.VariantOutputImpl ?: continue
            val origin = outputImpl.outputFileName.get()
            val new = origin.replace(moduleName, "PhonographPlus-${output.versionName.get()}")
            outputImpl.outputFileName.set(new)
        }
    }

    val name = appName.replace(Regex("\\s"), "") //remove white space
    onVariants(selector().withBuildType("release")) { variant ->
        tasks.registerPublishTask(name, variant)
    }
    onVariants(selector().withBuildType("intermediateRelease")) { variant ->
        tasks.registerPublishTask(name, variant)
    }
}

androidPublish {
    hashAlgorithm = setOf("SHA-256")
    nameStyle = listOf(NameSegment.VersionName, NameSegment.Favor)
}

tasks.whenTaskAdded {
    // disable due to reproducible build issues
    if (name.contains("Fdroid") && name.contains("ArtProfile")) {
        enabled = false
    }
}

dependencies {

    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.lifecycle.runtime)

    implementation(libs.androidx.annotation)
    implementation(libs.androidx.preference)

    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.viewpager2)

    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.percentlayout)
    implementation(libs.androidx.swiperefreshlayout)

    implementation(libs.androidx.media)
    implementation(libs.androidx.cardview)
    implementation(libs.androidx.palette)
    implementation(libs.bundles.androidx.room)
    implementation(libs.bundles.androidx.datastore)
    ksp(libs.androidx.room.compiler)

    implementation(libs.google.material)

    implementation(libs.bundles.compose)
    debugImplementation(libs.compose.ui.tooling)

    implementation(libs.bundles.themeUtil)

    implementation(libs.storageUtilities)
    implementation(libs.musicMetadataSource)
    implementation(libs.menuDsl)
    implementation(libs.seekArc)
    implementation(libs.slidingUpPanel)

    implementation(libs.composeMaterialDialogs)

    implementation(libs.okhttp3)
    implementation(libs.retrofit2)
    implementation(libs.coil)
    implementation(libs.coil.compose)
    implementation(libs.koin)

    implementation(libs.kotlinx.coroutines)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.licensesdialog)
    implementation(libs.jaudiotagger)
    implementation(libs.observablescrollview)
    implementation(libs.appIntro)
    implementation(libs.advrecyclerview)
    implementation(libs.recyclerviewFastscroll)
    implementation(libs.composeReorderable)
    implementation(libs.composePipette)
    implementation(libs.statusBarLyricsApi)
    implementation(libs.lyricsGetterAPi)

}
