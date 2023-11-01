plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
}

version = "0.0.1"

val ktorVersion = "2.3.5"
val okioVersion = "3.6.0"

val kotlinVersion: String by project

kotlin {
    jvm {
        jvmToolchain(17)
    }
    js(IR) {
        nodejs()
    }
    val hostOs = System.getProperty("os.name")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("myNative")
        hostOs == "Linux" -> linuxX64("myNative")
        hostOs.startsWith("Windows") -> mingwX64("myNative")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Internal
                api(project(":openai-kotlin-core"))

                // Jetbrains
                implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.6.0")

                // Ktor
                implementation("io.ktor:ktor-client-auth:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

                // External
                implementation("com.squareup.okio:okio:$okioVersion")
                implementation("org.slf4j:slf4j-simple:2.0.6")
            }
        }
        val commonTest by getting

        val jsMain by getting {
            dependencies {
                implementation("com.squareup.okio:okio-nodefilesystem:$okioVersion")
                implementation("io.ktor:ktor-client-js:$ktorVersion")
            }
        }
        val jsTest by getting

        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-java:$ktorVersion")
            }
        }
        val jvmTest by getting

        val myNativeMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-curl:$ktorVersion")
            }
        }
        val myNativeTest by getting
    }
}
