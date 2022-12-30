plugins {
    id("org.jetbrains.kotlin.jvm") version "1.8.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.22"
}

repositories {
    mavenCentral()
}

val kotlinVersion = "1.8.0"
val kotlinxSerializationVersion = "1.4.1"

val kotestVersion = "5.5.4"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlinxSerializationVersion")

    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-extensions-htmlreporter:$kotestVersion")
    testImplementation("io.kotest:kotest-extensions-junitxml:$kotestVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    reports {
        junitXml.required.set(false)
    }
    systemProperty("gradle.build.dir", project.buildDir)
}

kotlin {
    jvmToolchain(17)
}
