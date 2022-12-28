plugins {
    id("org.jetbrains.kotlin.jvm") version "1.7.22"
    id("com.diffplug.spotless") version "6.12.0"
}

group = "org.arvarik"
version = "0.0.1"

repositories {
    mavenCentral()
}

subprojects {
    apply(plugin = "com.diffplug.spotless")
    spotless {
        kotlin {
            ktlint("0.47.1").setUseExperimental(true)
        }
    }
}
