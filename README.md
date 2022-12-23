# OpenAI Kotlin Client
---
A kotlin client for OpenAI APIs to quickly insert into your Kotlin JVM apps

## Quick Start

###  Open AI setup

Create an [OpenAI account](https://beta.openai.com/signup) and sign up for a paid account. This will allow you to
create an API key that can be used to invocate the GPT-3 model endpoints.

Once you have signed up and created an [API key](https://beta.openai.com/account/api-keys), copy your API key and export
it in your local environment:

`export OPENAI_API_KEY=<your_api_key>`

If you want to do this on startup of every shell session, add the above statement to your bash/zsh rc file (in either
`~/.bashrc` or `~/.zshrc` respectively)

### Example Run

The `example-app` module in this package contains a simple `App.kt` kotlin function to invocate the GPT-3 /completions
endpoint using the Davinci model.

Once you have the Open AI setup done, you can run the following gradle command to test that your setup is working
correctly:

`./gradlew :example-app:run`

You should see something like the following output:

![OpenAI Example Run](/docs/readme/openai_example_run.png)

Keep in mind this is a generative model, so you might get different names for your squirrel!

### Recommended Tools

* [IntelliJ](https://www.jetbrains.com/idea/)
  * plugin: [Kotest](https://kotest.io/docs/intellij/intellij-plugin.html)

## Usage

In order to use this package into your app, you'll need to declare a dependency for it from the maven repository.

**TBD:**

### Gradle

```gradle
dependencies {
    implementation com.arvarik.openai-kotlin:openai-kotlin-client:0.0.1
}
```

## Overview

The openai-kotlin repository aims to provide a lightweight, consistent and error-prone kotlin client for OpenAI APIs

It's primary use it provide an out-of-the-box package for kotlin JVM apps to quickly use the OpenAI API.

The repository has two main modules.

### openai-kotlin-core

This module contains all the base classes for the OpenAI APIs. It mostly contains data classes that map to the OpenAI
API json schemas as well as any other common or utility functions.

### openai-kotlin-client

This module contains the main functionality to make requests to the OpenAI APIs and retrieve their responses.

## Upcoming

**TBD:**

- Full Open AI completion

- More OpenAI Configurations

- Security Features, including rate limiting

