rootProject.name = "openai-kotlin"

include("openai-kotlin-client")
include("openai-kotlin-core")

include("example-jvm")
project(":example-jvm").projectDir = file("example-apps/example-jvm")

include("example-js")
project(":example-js").projectDir = file("example-apps/example-js")

pluginManagement {
    repositories {
        gradlePluginPortal()
    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.id in setOf(
                    "org.jetbrains.kotlin.jvm",
                    "org.jetbrains.kotlin.multiplatform",
                    "org.jetbrains.kotlin.plugin.serialization")
                ) {
                val kotlinVersion: String by settings
                useVersion(kotlinVersion)
            }
        }
    }
}
