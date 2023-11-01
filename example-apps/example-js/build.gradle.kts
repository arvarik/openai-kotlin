plugins {
    kotlin("multiplatform")
}

kotlin {
    js {
        nodejs {
            binaries.executable()
        }
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
                api(project(":openai-kotlin-client"))
            }
        }
    }
}
