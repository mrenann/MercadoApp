import io.gitlab.arturbosch.detekt.Detekt
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.detekt)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.kotlinx.kover)
    alias(libs.plugins.ksp)
}

allprojects {
    detekt {
        toolVersion = libs.versions.detekt.get()
        config.setFrom(file("$rootDir/config/detekt/detekt.yml"))
    }
    tasks.withType<Detekt>().configureEach {
        reports {
            xml.required.set(true)
            html.required.set(false)
            txt.required.set(false)
        }
    }
    tasks.withType<Detekt>().configureEach {
        reports {
            xml.outputLocation.set(file("$rootDir/reports/detekt.xml"))
        }
    }
}

android {
    namespace = "com.mrenann.mercadolivre"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.mrenann.mercadolivre"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
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
    buildFeatures {
        compose = true
    }
}

ktlint {
    android = true
    reporters {
        reporter(ReporterType.PLAIN)
        reporter(ReporterType.SARIF)
        reporter(ReporterType.CHECKSTYLE)
    }
}


kover {
    reports {
        total {
            xml {
                onCheck = true
            }
        }

        verify {
            rule {
                minBound(50)
            }
        }

        filters {
            excludes {
                annotatedBy(
                    "androidx.compose.runtime.Composable",
                    "androidx.compose.ui.tooling.preview.Preview"
                )
                packages(
                    "com.mrenann.mercadolivre.*.presentation.screens*",
                    "com.mrenann.mercadolivre.*.presentation.components*",
                    "com.mrenann.mercadolivre.ui.theme",
                    "com.mrenann.mercadolivre.*.di"
                )
            }
        }
        total {
            xml {
                onCheck = true
            }

            html {
                onCheck = true
            }
        }
    }

}

ksp {
    arg("lyricist.internalVisibility", "true")
    arg("lyricist.generateStringsProperty", "true")
}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.bundles.voyager)
    implementation(libs.lyricist)
    ksp(libs.lyricist.processor)
    implementation(libs.composeIcons.evaIcons)
    implementation(libs.code.gson)
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.compose)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation(libs.zoomable)
    implementation(libs.bundles.room)
    ksp(libs.androidx.room.compiler)

    // TESTS
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.turbine)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.androidx.core)
    testImplementation(libs.kotlinx.coroutines.test)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}