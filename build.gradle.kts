plugins {
    kotlin("jvm") version "2.3.20"
    id("com.google.devtools.ksp") version "2.3.20-1.0.31"
    application
}

group = "com.example"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    // Core orchestration + KSP named builders
    implementation("io.github.damian-rafael-lattenero:kap-core:2.5.0")
    implementation("io.github.damian-rafael-lattenero:kap-ksp-annotations:2.5.0")
    ksp("io.github.damian-rafael-lattenero:kap-ksp:2.5.0")

    // Optional: resilience patterns (Schedule, CircuitBreaker, Resource, timeoutRace)
    // implementation("io.github.damian-rafael-lattenero:kap-resilience:2.5.0")

    // Optional: Arrow integration (parallel validation with error accumulation)
    // implementation("io.github.damian-rafael-lattenero:kap-arrow:2.5.0")

    // Optional: Ktor server plugin
    // implementation("io.github.damian-rafael-lattenero:kap-ktor:2.5.0")

    // Optional: test matchers
    // testImplementation("io.github.damian-rafael-lattenero:kap-kotest:2.5.0")
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("MainKt")
}
