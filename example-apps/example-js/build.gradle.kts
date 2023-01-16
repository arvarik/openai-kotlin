plugins {
    kotlin("js")
}

dependencies {
    api(project(":openai-kotlin-client"))
}

kotlin {
    js {
        nodejs {
            binaries.executable()
        }
    }
}
