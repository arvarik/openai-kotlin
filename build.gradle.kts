plugins {
    kotlin("jvm")
    id("com.diffplug.spotless") version "6.22.0"
    `maven-publish`
}

val GITHUB_USER: String? by project
val GITHUB_TOKEN: String? by project

val kotlinVersion: String by project

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "com.diffplug.spotless")
    spotless {
        kotlin {
            ktlint("1.0.1")
            target("**/*.kt")
            endWithNewline()
        }
    }
}

configure(listOf(project(":openai-kotlin-core"), project(":openai-kotlin-client"))) {
    apply(plugin = "maven-publish")
    group = "org.arvarik.openai-kotlin"

    publishing {
        repositories {
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/arvarik/openai-kotlin")
                credentials {
                    username = GITHUB_USER
                    password = GITHUB_TOKEN
                }
            }
        }
    }
}
