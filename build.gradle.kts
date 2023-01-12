plugins {
    kotlin("jvm") version "1.8.0"
    id("com.diffplug.spotless") version "6.12.0"
    `maven-publish`
}

val GITHUB_USER: String? by project
val GITHUB_TOKEN: String? by project

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

configure(listOf(project(":openai-kotlin-core"), project(":openai-kotlin-client"))) {
    apply(plugin = "maven-publish")
    group = "com.arvarik.openai-kotlin"

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
