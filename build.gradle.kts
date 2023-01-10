plugins {
    kotlin("jvm") version "1.8.0"
    id("com.diffplug.spotless") version "6.12.0"
}

group = "org.arvarik"
version = "0.0.1"

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "com.diffplug.spotless")
    spotless {
        kotlin {
            target("**/*.kt")
            ktlint("0.47.1").setUseExperimental(true)
            endWithNewline()
        }
    }
}
