plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.8.0"
}

val kotlinVersion = "1.8.0"
val kotlinxSerializationVersion = "1.4.1"
val kotestVersion = "5.5.4"

kotlin {
    jvm {
        jvmToolchain(17)

        tasks.named<Test>("jvmTest") {
            useJUnitPlatform()
            reports {
                junitXml.required.set(false)
            }
            systemProperty("gradle.build.dir", project.buildDir)
        }
    }
    js(IR) {
        browser()
    }
    val hostOs = System.getProperty("os.name")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        hostOs.startsWith("Windows") -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinxSerializationVersion")
            }
        }

        val jvmTest by getting {
            dependencies {
                implementation("io.kotest:kotest-runner-junit5:$kotestVersion")
                implementation("io.kotest:kotest-extensions-htmlreporter:$kotestVersion")
                implementation("io.kotest:kotest-extensions-junitxml:$kotestVersion")

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion")

                implementation("org.skyscreamer:jsonassert:1.5.0")
            }
        }
    }
}
