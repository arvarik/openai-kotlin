plugins {
    kotlin("jvm")
    application
}

val kotlinVersion: String by project

dependencies {
    api(project(":openai-kotlin-client"))

    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("com.arvarik.openai.example.jvm.AppKt")
}
