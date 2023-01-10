rootProject.name = "openai-kotlin"

include("openai-kotlin-client")
include("openai-kotlin-core")

include("example-jvm")
project(":example-jvm").projectDir = file("example-apps/example-jvm")
