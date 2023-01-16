plugins {
    kotlin("jvm") version "1.8.0"
    application
}

dependencies {
    api(project(":openai-kotlin-client"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("com.arvarik.openai.example.jvm.AppKt")
}
